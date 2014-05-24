package experiment.netty.web;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-20 16:14
 * <p/>
 * 学习用Netty实现http server
 */

public class WebServer{
    private final int port;

    public WebServer( int port ){
        this.port = port;
    }

    public static void main( String[] args ) throws Exception{
        int port;
        if( args.length > 0 ) {
            port = Integer.parseInt( args[0] );
        } else {
            port = 8000;
        }
        new WebServer( port ).run();
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup( 1 );
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new WebServerInitializer() );

            b.bind( port ).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
