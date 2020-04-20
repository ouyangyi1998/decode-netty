import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Sheva
 * @date 2020/4/20 10:12
 * @description
 */
public class HttpServer {

    public static final int PORT = 8888;

    public static EventLoopGroup group = new NioEventLoopGroup();
    public static ServerBootstrap serverBootstrap = new ServerBootstrap();

    public static void main(String[] args) throws Exception {
        try{
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerHandlerInit());

            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
}
