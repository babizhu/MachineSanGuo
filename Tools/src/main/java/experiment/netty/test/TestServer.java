package experiment.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-23 11:37
 */

public class TestServer{
    public static final int port = 8000;

    public static void main( String[] args ){
        new TestServer().run();
    }

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new TestInitializer() );
            bootstrap.option( ChannelOption.SO_BACKLOG, 100 );

            bootstrap.bind( port ).sync().channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
