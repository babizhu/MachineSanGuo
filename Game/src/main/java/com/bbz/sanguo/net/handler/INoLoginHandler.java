package com.bbz.sanguo.net.handler;

import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;


/**
 * user         LIUKUN
 * time         2014-6-6 11:49
 * 所有无需登录就可以执行的处理器的接口
 */

public interface INoLoginHandler extends IHandler{

    public abstract void run( MsgProtocol.Request request, MsgProtocol.Response.Builder responseBuilder, ChannelHandlerContext ctx );
}
