package experiment.netty.lingkong.server;

import experiment.netty.lingkong.request.AbstractResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * user         LIUKUN
 * time         2014-5-23 18:00
 */

public class RequestEncoder extends MessageToByteEncoder<AbstractResponse>{

    @Override
    protected void encode( ChannelHandlerContext ctx, AbstractResponse msg, ByteBuf out ) throws Exception{
        out.writeInt( msg.getResult() );
    }
}
