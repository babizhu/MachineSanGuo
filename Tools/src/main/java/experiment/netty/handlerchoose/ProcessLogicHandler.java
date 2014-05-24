package experiment.netty.handlerchoose;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-5-24 16:01
 */

public class ProcessLogicHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        super.channelRead( ctx, msg );
        System.out.println( "ProcessLogicHandler.channelRead" );
    }
}
