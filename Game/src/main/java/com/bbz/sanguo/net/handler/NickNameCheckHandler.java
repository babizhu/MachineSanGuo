package com.bbz.sanguo.net.handler;

import io.netty.channel.ChannelHandlerContext;

import static com.bbz.sanguo.net.protobuf.MsgProtocol.*;

/**
 * user         LIUKUN
 * time         2014-6-6 17:03
 * 检测玩家的昵称是否被占用
 */

public class NickNameCheckHandler implements INoLoginHandler{
    @Override
    public void run( Request request, Response.Builder responseBuilder, ChannelHandlerContext ctx ){
        String nickName = request.getNickNameCheck().getNickName();


        NickNameCheckResponse.Builder result = NickNameCheckResponse.newBuilder();
        result.setIsDuplicate( true );


        /****************************设返回值***************************/
        responseBuilder.setNickNameCheck( result );
    }
}
