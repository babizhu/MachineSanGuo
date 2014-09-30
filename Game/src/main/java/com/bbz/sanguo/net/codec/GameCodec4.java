package com.bbz.sanguo.net.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-29 15:44
 * 继承于MessageToMessageCodec
 */

public class GameCodec4 extends MessageToMessageCodec<Integer,String>{
    @Override
    protected void encode( ChannelHandlerContext ctx, String msg, List<Object> out ) throws Exception{

    }

    @Override
    protected void decode( ChannelHandlerContext ctx, Integer msg, List<Object> out ) throws Exception{

    }
}