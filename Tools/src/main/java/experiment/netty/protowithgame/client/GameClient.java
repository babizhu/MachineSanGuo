package experiment.netty.protowithgame.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-27 18:22
 */

public class GameClient{

    public static final int PORT = 8000;

    public static void main( String[] args ){
        new GameClient().run();
    }

    private void run(){
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
            bootstrap.handler( new GameClientInitializer() );

            Channel channel = bootstrap.connect( "localhost", PORT ).sync().channel();

            GameClientHandler handler = channel.pipeline().get( GameClientHandler.class );
            handler.MissionShow();


//            int ret = handler.login( "bbz"+ RandomUtil.getInt( 1000 ), "pass123" );

            //System.out.println( "登录的结果 " + ret );


            for( int i = 0; i < 0; i++ ) {
                Thread.sleep( 10 );
                //handler.MissionShow();
                handler.login( "bbz", "pass" );
            }
            //channel.close();

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }

    }

}
