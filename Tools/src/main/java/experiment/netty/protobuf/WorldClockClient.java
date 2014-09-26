package experiment.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;
import java.util.List;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 14:23
 */

public class WorldClockClient{

    //    static final boolean SSL = System.getProperty( "ssl" ) != null;
//    static final String HOST = System.getProperty( "host", "127.0.0.1" );
//    static final int PORT = Integer.parseInt( System.getProperty( "port", "8463" ) );
    static final List<String> CITIES = Arrays.asList( System.getProperty(
            "cities", "Asia/Seoul,Europe/Berlin,America/Los_Angeles" ).split( "," ) );
//            "cities", "Asia/Seoul" ).split( "," ) );

    public static void main( String[] args ){
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
            bootstrap.handler( new WorldClockClientInitializer() );

            Channel channel = bootstrap.connect( "localhost", 8001 ).sync().channel();


            WorldClockClientHandler handler = channel.pipeline().get( WorldClockClientHandler.class );

            List<String> response = handler.getLocalTimes( CITIES );
            System.out.println( "response.size() :" + response.size() );
            channel.close();
            for( int i = 0; i < CITIES.size(); i++ ) {
                System.out.format( "%28s: %s%n", CITIES.get( i ), response.get( i ) );
            }

        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

}
