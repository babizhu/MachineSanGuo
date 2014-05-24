package experiment.netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-5-23 14:55
 */

public class TimeDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        if( in.readableBytes() >= 4 ) {
            out.add( in.readInt() );
        }
    }
}
