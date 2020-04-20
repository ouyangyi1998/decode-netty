import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

/**
 * @author Sheva
 * @date 2020/4/20 10:33
 * @description
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse httpResponse = (FullHttpResponse)msg;
        System.out.println(httpResponse.headers());

        ByteBuf content = httpResponse.content();
        System.out.println(content.toString(CharsetUtil.UTF_8));
        content.release();
    }
}
