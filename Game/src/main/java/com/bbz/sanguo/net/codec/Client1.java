package com.bbz.sanguo.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-30 10:44
 */

public class Client1 extends ReplayingDecoder<Void>{


    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        int charLen = in.readInt();
        for( int i = 0; i < charLen; i++ ) {
            System.out.println( in.readByte() );
        }
    }
}