import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author Sheva
 * @date 2020/4/20 10:18
 * @description
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        String ret = "";

        try{
            String uri = httpRequest.uri();
            String data = httpRequest.content().toString(CharsetUtil.UTF_8);

            HttpMethod method = httpRequest.method();
            if (!"/bxs".equalsIgnoreCase(uri)){
                ret = "非法请求路径：" + uri;
                response(ret, ctx, HttpResponseStatus.BAD_REQUEST);
                return;
            }

            if (HttpMethod.GET.equals(method)){
                System.out.println("客户端请求数据内容： " + data);
                ret = "服务端接受到数据，接受到的数据为： " + data;
                response(ret, ctx, HttpResponseStatus.OK);
            }
            if (HttpMethod.POST.equals(method)){
                //TODO
            }
            if (HttpMethod.PUT.equals(method)){
                //TODO
            }
        }catch (Exception e){
            System.out.println("服务器处理失败。。。");
        }finally {
            httpRequest.release();
        }
    }

    private void response(String data, ChannelHandlerContext ctx, HttpResponseStatus status){
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(data, CharsetUtil.UTF_8));
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
