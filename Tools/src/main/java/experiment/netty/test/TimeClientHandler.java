package experiment.netty.test;

import com.bbz.tool.time.TimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-5-23 14:17
 */

public class TimeClientHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        try {
            if( buf.readableBytes() >= 4 ) {
                int second = buf.readInt();
                ctx.close();
                System.out.println( TimeUtil.secondsToDateStr( second ) );
            }
        } finally {
            buf.release();
        }
    }
}
