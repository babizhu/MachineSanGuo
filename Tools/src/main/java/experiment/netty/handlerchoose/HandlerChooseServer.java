package experiment.netty.handlerchoose;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-24 15:52
 * <p/>
 * 动态选择handler，连接上之后必须先登录，然后就可以不用登陆了（去掉登录handler）
 */

public class HandlerChooseServer{
    public static final int PORT = 8000;

    public static void main( String[] args ){
        new HandlerChooseServer().run();
    }

    private void run(){
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new HandlerChooseInitializer() );
            bootstrap.option( ChannelOption.SO_BACKLOG, 100 );

            bootstrap.bind( PORT ).sync().channel().closeFuture().sync();

        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
