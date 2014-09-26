package com.bbz.sanguo.net;

import com.bbz.sanguo.lanch.ServerCfg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

public class GameServerTest{

    @Test
    public void testDispatcherChange() throws Exception{
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
//            bootstrap.channel( OioSocketChannel.class );
            bootstrap.handler( new GameClientInitializer() );

            Channel channel = bootstrap.connect( "localhost", ServerCfg.PORT ).sync().channel();

            GameClientHandler handler = channel.pipeline().get( GameClientHandler.class );

            for( int i = 0; i < 1; i++ ) {

                handler.missionShow();
                handler.login( "bbz", "pass" );
                handler.missionShow();
                handler.login( "bbz", "pass" );
            }

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            Thread.sleep( 1000000 );
            worker.shutdownGracefully();
        }


    }
}