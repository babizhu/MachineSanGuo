package com.bbz.sanguo.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-28 15:33
 */

public class GameCodec1 extends ByteToMessageDecoder{
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        in.markReaderIndex();
        if( in.readableBytes() < 4+4){
            return;
        }
        int nameLen = in.readInt();
        System.out.println( "需要长度:" + nameLen + ",实际长度：" + in.readableBytes() );
        if( in.readableBytes() < nameLen ){

            in.resetReaderIndex();
            return;
        }
        String name = new String( in.readBytes(nameLen).array() );
        int age;
        if( in.readableBytes() < 4 ){
            in.resetReaderIndex();
            return;
        }
        age = in.readInt();

        out.add( new Student( name, age ) );
    }

    @Override
    protected void decodeLast( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        System.out.println( "GameCodec1.decodeLast:" + ctx.channel().remoteAddress() );
    }
}
