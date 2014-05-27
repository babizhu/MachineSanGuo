package experiment.netty.protobuf.test.handler;

import com.google.protobuf.GeneratedMessage;
import experiment.protocolgen.MsgProtocol;

import static experiment.protocolgen.MsgProtocol.LoginRequest;
import static experiment.protocolgen.MsgProtocol.MSG;

/**
 * user         LIUKUN
 * time         2014-5-26 18:44
 */

public class LoginHandler extends AbstractHandler<LoginRequest>{

    MSG msg = MSG.Login_Request;


    @Override
    public GeneratedMessage run( LoginRequest request ){
        request.getPassword();
        request.getUsername();

        //run logic

        MsgProtocol.LoginResponse.Builder builder = MsgProtocol.LoginResponse.newBuilder();
        builder.setRet( 32 );

        return builder.build();
    }
}
