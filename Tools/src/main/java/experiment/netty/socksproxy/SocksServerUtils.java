package experiment.netty.socksproxy;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

/**
 * user         LIUKUN
 * time         2014-5-24 14:43
 */

public class SocksServerUtils{
    private SocksServerUtils(){
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    public static void closeOnFlush( Channel ch ){
        if( ch.isActive() ) {
            ch.writeAndFlush( Unpooled.EMPTY_BUFFER ).addListener( ChannelFutureListener.CLOSE );
        }
    }
}
