import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @author Sheva
 * @date 2020/4/20 10:14
 * @description
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("responseEncoder", new HttpResponseEncoder());
        pipeline.addLast("requestDecode", new HttpRequestDecoder());

        pipeline.addLast("objectAggregator", new HttpObjectAggregator(1024));
        pipeline.addLast("contentCompressor", new HttpContentCompressor());

        pipeline.addLast("httpServerHandler", new HttpServerHandler());
    }
}
