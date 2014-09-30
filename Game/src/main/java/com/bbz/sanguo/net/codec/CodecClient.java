package com.bbz.sanguo.net.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014/9/27 0027 21:02
 */

public class CodecClient{

    void run() throws InterruptedException{
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
//            bootstrap.channel( OioSocketChannel.class );
            bootstrap.handler( new CodecClientInitializer() );

            Channel channel = bootstrap.connect( "localhost", CodecServer.PORT ).sync().channel();

            CodecClientHandler1 handler = channel.pipeline().get( CodecClientHandler1.class );

            for( int i = 0; i < 1; i++ ) {

                handler.send();
            }

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            Thread.sleep( 1000000 );
            worker.shutdownGracefully();
        }

    }

    public static void main( String[] args ) throws InterruptedException{
        new CodecClient().run();
    }
}
