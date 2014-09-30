package com.bbz.sanguo.net.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * user         LIUKUN
 * time         2014/9/27 0027 21:04
 */

public class CodecClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{

//        ch.pipeline().addLast( new GameCodec() );
        ch.pipeline().addLast( new CodecClientHandler1() );
    }
}
