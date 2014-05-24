package experiment.netty.socksproxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * user         LIUKUN
 * time         2014-5-24 14:28
 */

public class SocksServer{

    public static final int PORT = 8000;

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group( boss, worker );

            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.handler( new LoggingHandler( LogLevel.INFO ) );
            bootstrap.childHandler( new SocksServerInitializer() );
            bootstrap.option( ChannelOption.SO_BACKLOG, 100 );
            bootstrap.childOption( ChannelOption.SO_KEEPALIVE, true );
            bootstrap.bind( PORT ).sync().channel().closeFuture().sync();

        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();

        }
    }
}
