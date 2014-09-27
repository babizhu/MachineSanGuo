package com.bbz.sanguo.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-26 11:29
 */

public class GameCodec extends ByteToMessageCodec<Student>{
    @Override
    protected void encode( ChannelHandlerContext ctx, Student msg, ByteBuf out ) throws Exception{
        out.writeInt( msg.getName().getBytes().length );
        out.writeBytes( msg.getName().getBytes() );
        out.writeInt( msg.getAge() );

    }

    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        in.markReaderIndex();
        if( in.readableBytes() < 4+4){
            return;
        }
        int nameLen = in.readInt();
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
}
