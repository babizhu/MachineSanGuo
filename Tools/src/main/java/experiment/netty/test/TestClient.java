package experiment.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-23 13:49
 */

public class TestClient{
    public static final int port = 8000;

    public static void main( String[] args ){
        for( int i = 0; i < 10000; i++ ) {

            new TestClient().run();
        }
    }

    private void run(){
        EventLoopGroup worker = new NioEventLoopGroup( 1 );
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
            bootstrap.option( ChannelOption.SO_KEEPALIVE, true );
            bootstrap.handler( new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel( SocketChannel ch ) throws Exception{
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast( new TimeClientHandler() );
                }
            } );

            bootstrap.connect( "localhost", port ).sync().channel().close().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
