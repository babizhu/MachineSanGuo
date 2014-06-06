package experiment.netty.raw;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014-6-4 15:47
 */

public class RawClient{
    public static final int PORT = 8000;

    public static void main( String[] args ) throws InterruptedException{
        new RawClient().run();
    }

    void run() throws InterruptedException{
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group( new NioEventLoopGroup() );
        bootstrap.channel( NioSocketChannel.class );
        bootstrap.handler( new RawClientHandler() );

        Channel channel = bootstrap.connect( "localhost", PORT ).sync().channel();
//
//
        RawClientHandler handler = channel.pipeline().get( RawClientHandler.class );
        for( int i = 0; i < 10; i++ ) {
            Thread.sleep( 1 );

            handler.sendMsg();
        }
//        Thread.
    }
}
