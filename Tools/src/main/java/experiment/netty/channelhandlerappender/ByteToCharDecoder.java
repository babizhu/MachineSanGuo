package experiment.netty.channelhandlerappender;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-5-20 15:19
 */

public class ByteToCharDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        while( in.readableBytes() > 2 ) {
            out.add( Character.valueOf( in.readChar() ) );
        }
    }
}
