package experiment.netty.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014-6-4 14:29
 */

public class TelnetClient{
    public static final int PORT = 8000;

    public static void main( String[] args ){
        new TelnetClient().run();
    }

    void run(){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group( new NioEventLoopGroup() );
        bootstrap.channel( NioSocketChannel.class );
        bootstrap.handler( new TelnetClientHandler(){
        } );
        //bootstrap.
        try {
            Channel channel = bootstrap.connect( "localhost", PORT ).sync().channel();
            channel.writeAndFlush( "abcdhgfhfghfghfghfghfghhhffhfhfghfghfhhfgh".getBytes() );
            System.out.println( 4545 );
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }


    }
}
