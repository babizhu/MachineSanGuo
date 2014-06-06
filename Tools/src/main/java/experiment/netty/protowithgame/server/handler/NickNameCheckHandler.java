package experiment.netty.protowithgame.server.handler;

import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-6-6 17:03
 * 检测玩家的昵称是否被占用
 */

public class NickNameCheckHandler implements IHandlerWithoutUser{
    @Override
    public void run( MsgProtocol.Request request, MsgProtocol.Response.Builder responseBuilder, ChannelHandlerContext ctx ){
        String nickName = request.getNickNameCheck().getNickName();


        MsgProtocol.NickNameCheckResponse.Builder result = MsgProtocol.NickNameCheckResponse.newBuilder();
        result.setIsDuplicate( true );


        /****************************设返回值***************************/
        responseBuilder.setNickNameCheck( result );
    }
}
