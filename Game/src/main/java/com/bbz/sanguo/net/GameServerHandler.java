package com.bbz.sanguo.net;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-9-19 16:36
 * 玩家登录之后的处理逻辑
 */

public class GameServerHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        System.out.println( "GameServerHandler.channelRead" );
    }
}
