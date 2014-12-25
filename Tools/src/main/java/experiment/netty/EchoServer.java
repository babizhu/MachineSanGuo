package experiment.netty;

/**
 * user         LIUKUN
 * time         2014-5-7 11:28
 * 用netty写的echo服务器程序
 */


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Echoes back any received data from a client.
 */
public class EchoServer{

    private final int port;

    public EchoServer( int port ){
        this.port = port;
    }

    public static void main( String[] args ) throws Exception{


        int port;
        if( args.length > 0 ) {
            port = Integer.parseInt( args[0] );
        } else {
            port = 5000;
        }
        new EchoServer( port ).run();
    }

    public void run() throws Exception{
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup( 1 );
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .option( ChannelOption.SO_BACKLOG, 100 )
                    .handler( new EchoHandler1() )
                    .childHandler( new ChannelInitializer<SocketChannel>(){
                        @Override
                        public void initChannel( SocketChannel ch ) throws Exception{
                            ch.pipeline().addLast(
//                                    new LoggingHandler(LogLevel.TRACE),

                                    new EchoServerHandler() );

                        }
                    } );

            // Start the server.
            ChannelFuture f = b.bind( port ).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}