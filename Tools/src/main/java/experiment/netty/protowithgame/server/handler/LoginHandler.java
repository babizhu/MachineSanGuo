package experiment.netty.protowithgame.server.handler;

import com.bbz.tool.common.RandomUtil;
import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;

import static experiment.protocolgen.MsgProtocol.LoginRequest;
import static experiment.protocolgen.MsgProtocol.Response;

/**
 * user         LIUKUN
 * time         2014-5-28 14:02
 */

public class LoginHandler implements IHandlerWithoutUser{

    @Override
    public void run( MsgProtocol.Request request, Response.Builder responseBuilder, ChannelHandlerContext ctx ){
        /****************************获取参数***************************/
        LoginRequest login = request.getLogin();
        String uname = login.getUserName();
        String password = login.getPassword();
        System.out.println( "uname " + uname + " pass " + password );


        /****************************逻辑处理***************************/
        MsgProtocol.LoginResponse.Builder result = MsgProtocol.LoginResponse.newBuilder();
        result.setRet( RandomUtil.getInt( 5000 ) );


        /****************************设返回值***************************/
        responseBuilder.setLogin( result );

    }
}
