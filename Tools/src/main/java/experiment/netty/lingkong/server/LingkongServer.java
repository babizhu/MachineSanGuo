package experiment.netty.lingkong.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-23 17:20
 * lingkong服务器
 */

public class LingkongServer{

    public static final int port = 8000;

    public static void main( String[] args ){
        new LingkongServer().run();
    }

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new LingkongInitializer() );
            bootstrap.option( ChannelOption.SO_BACKLOG, 100 );
            bootstrap.childOption( ChannelOption.SO_KEEPALIVE, true );

            ChannelFuture future = bootstrap.bind( port ).sync();
            future.channel().closeFuture().sync();

        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
