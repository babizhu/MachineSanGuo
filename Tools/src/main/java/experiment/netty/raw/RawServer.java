package experiment.netty.raw;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-6-4 15:38
 * 纯的二进制流，不需要任何编码解码过程
 */

public class RawServer{
    public static final int PORT = 8000;

    public static void main( String[] args ){
        new RawServer().run();
        ;
    }

    void run(){
        NioEventLoopGroup boss = new NioEventLoopGroup( 1 );
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group( boss, worker );

        bootstrap.channel( NioServerSocketChannel.class );
        bootstrap.childHandler( new RawHandler() );

        try {
            bootstrap.bind( PORT ).sync().channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
