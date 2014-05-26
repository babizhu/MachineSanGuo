package experiment.netty.protobuf.test.handler;

import com.google.protobuf.GeneratedMessage;

import static experiment.protocolgen.MsgProtocol.*;

/**
 * user         LIUKUN
 * time         2014-5-26 18:44
 */

public class LoginHandler extends AbstractHandler{

    MSG msg = MSG.Login_Request;

    @Override
    GeneratedMessage run( GeneratedMessage param ){
        LoginRequest p = (LoginRequest) param;
        p.getPassword();
        p.getUsername();

        //run logic

        LoginResponse.Builder builder = LoginResponse.newBuilder();
        builder.setCompany( 32 );
        return builder.build();

    }
}
