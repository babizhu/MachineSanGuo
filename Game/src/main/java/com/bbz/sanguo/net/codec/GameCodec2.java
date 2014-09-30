package com.bbz.sanguo.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-29 11:06
 */

public class GameCodec2 extends ReplayingDecoder<Void>{
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        System.out.println( "GameCodec2.decode" );
        int nameLen = in.readInt();
        String name = new String( in.readBytes(nameLen).array() );
        int age = in.readInt();
        out.add( new Student( name, age ) );

    }
}
