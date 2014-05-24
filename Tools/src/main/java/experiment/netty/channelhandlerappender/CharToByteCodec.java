package experiment.netty.channelhandlerappender;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-5-20 15:12
 * 一个类完成编码和解码两个工作
 */

public class CharToByteCodec extends ByteToMessageCodec{
    @Override
    protected void encode( ChannelHandlerContext ctx, Object msg, ByteBuf out ) throws Exception{
//        CombinedChannelDuplexHandler c = new

    }

    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List out ) throws Exception{

    }
}
