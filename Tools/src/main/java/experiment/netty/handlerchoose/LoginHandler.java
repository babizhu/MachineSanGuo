package experiment.netty.handlerchoose;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

/**
 * user         LIUKUN
 * time         2014-5-24 16:00
 */

public class LoginHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        super.channelRead( ctx, msg );
        System.out.println( "LoginHandler.channelRead" );
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.addLast( new ProcessLogicHandler() );

        pipeline.remove( this );

    }
}
