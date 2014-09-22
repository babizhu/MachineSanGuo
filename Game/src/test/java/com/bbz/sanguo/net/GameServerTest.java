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
            bootstrap.handler( new GameClientInitializer() );

            Channel channel = bootstrap.connect( "localhost", ServerCfg.PORT ).sync().channel();

            GameClientHandler handler = channel.pipeline().get( GameClientHandler.class );
//            handler.MissionShow();


//            int ret = handler.login( "bbz"+ RandomUtil.getInt( 1000 ), "pass123" );

            //System.out.println( "登录的结果 " + ret );


            handler.login( "bbz", "pass" );
//            for( int i = 0; i < 0; i++ ) {
//                Thread.sleep( 10 );
//            }
            //channel.close();
//            System.out.println("4444444444444444444444444444444444444444");
//            Thread.sleep( 1000 );
//            handler.login( "bbz", "pass" );
//            Thread.sleep( 1000 );
//            handler.MissionShow();
//            Thread.sleep( 1000 );
//            handler.login( "bbz", "pass" );

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }

    }
}