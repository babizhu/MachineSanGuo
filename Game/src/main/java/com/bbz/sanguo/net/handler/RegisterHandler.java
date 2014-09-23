package com.bbz.sanguo.net.handler;

import com.bbz.sanguo.net.protobuf.MsgProtocol;
import com.bbz.sanguo.net.protobuf.User;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-9-23 17:40
 */

public class RegisterHandler implements INoLoginHandler{
    @Override
    public void run( MsgProtocol.Request request, MsgProtocol.Response.Builder responseBuilder, ChannelHandlerContext ctx ){
        User.RegisterRequest register = request.getRegister();
        String mobile = register.getMobile();
        String userName = register.getUserName();
        String password = register.getPassword();

    }
}
