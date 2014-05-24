package experiment.netty.lingkong.server;

import experiment.netty.lingkong.request.AbstractRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-5-23 17:33
 * 解码器 把byte转换为pojo
 */

public class RequestDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        if( in.readableBytes() >= AbstractRequest.MAX_SIZE ) {
            out.add( new AbstractRequest( in.readLong(), in.readShort() ) );
        }
    }
}
