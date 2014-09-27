package com.bbz.sanguo.net.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014/9/27 0027 21:00
 */

public class CodecServerHandler extends SimpleChannelInboundHandler<Student>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Student msg ) throws Exception{
        System.out.println( msg );
    }
}
