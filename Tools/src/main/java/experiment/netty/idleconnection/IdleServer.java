package experiment.netty.idleconnection;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-20 17:07
 * <p/>
 * 测试Idle连接，当有客户端连接上来，超过60秒没有数据往来的时候，会发送一个心跳包，如果此心跳包还没有回应，则切断连接
 */

public class IdleServer{
    public static final int port = 8000;

    public static void main( String[] args ) throws InterruptedException{
        new IdleServer().run();
    }

    private void run() throws InterruptedException{
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( boss, worker )
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new IdleServerInitHandler() );

            b.bind( port ).sync().channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
