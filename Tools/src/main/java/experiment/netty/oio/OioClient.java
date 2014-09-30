package experiment.netty.oio;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.local.LocalAddress;

/**
 * user         LIUKUN
 * time         2014-9-30 13:50
 */

public class OioClient extends ChannelHandlerAdapter{
    static final String PORT = System.getProperty("port", "test_port");

    public static void main( String[] args ){
        final LocalAddress addr = new LocalAddress(PORT);
        System.out.println( addr);
    }
}
