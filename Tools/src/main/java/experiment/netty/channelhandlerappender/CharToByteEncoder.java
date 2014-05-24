package experiment.netty.channelhandlerappender;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * user         LIUKUN
 * time         2014-5-20 15:15
 */

public class CharToByteEncoder extends MessageToByteEncoder<Character>{
    @Override
    protected void encode( ChannelHandlerContext ctx, Character msg, ByteBuf out ) throws Exception{
        out.writeChar( msg );
    }
}
