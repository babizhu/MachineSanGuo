package experiment.netty.protowithgame.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-27 19:43
 */

public class GameServer{
    public static final int PORT = 8000;

    public static void main( String[] args ){
        new GameServer().run();
    }

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new GameServerInitializer() );

            bootstrap.bind( PORT ).sync().channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
