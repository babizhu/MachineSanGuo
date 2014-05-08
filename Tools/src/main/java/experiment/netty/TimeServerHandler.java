package experiment.netty;

import com.bbz.tool.time.SystemTimer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-5-8 13:17
 */

public class TimeServerHandler extends ChannelHandlerAdapter{
    @Override
    public void channelActive( final ChannelHandlerContext ctx ) throws Exception{
        final ByteBuf buffer = ctx.alloc().buffer( 4 );
        buffer.writeInt( SystemTimer.currentTimeSecond() );

        ChannelFuture channelFuture = ctx.writeAndFlush( buffer );
        channelFuture.addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
                ctx.close();
            }
        } );

    }
}
