package experiment.netty.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 13:50
 * Netty IO 官网上使用protbuf的例子
 */

public class WorldClockServer{
    public static final int PORT = 8001;

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new WorldClockServerInitializer() );
            bootstrap.option( ChannelOption.SO_BACKLOG, 100 );

            bootstrap.bind( PORT ).sync().channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }


    }

    public static void main( String[] args ){
        new WorldClockServer().run();
    }
}
