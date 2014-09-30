package com.bbz.sanguo.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-9-30 10:49
 */

public class CodecClientHandler1 extends ChannelHandlerAdapter{
    private Channel channel;

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
//        super.channelRead( ctx, msg );
        ByteBuf in = (ByteBuf) msg;
        int charLen = in.readInt();
        System.out.println( "长度为：" + charLen );
        for( int i = 0; i < charLen; i++ ) {
            System.out.println( in.readByte() );
        }
    }

    public void send(){
        String msg = "aaayyyysed";
        ByteBuf buf = channel.alloc().buffer();
        buf.writeInt( msg.length() );
        buf.writeBytes( msg.getBytes() );

        channel.writeAndFlush( buf );

    }

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ) throws Exception{
        this.channel = ctx.channel();
    }
}
