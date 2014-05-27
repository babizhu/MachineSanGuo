package experiment.netty.protowithgame.server;

import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-5-27 19:48
 */

public class GameServerHandler extends SimpleChannelInboundHandler<MsgProtocol.Message>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Message msg ) throws Exception{
        String uname = msg.getRequest().getLogin().getUsername();
        String password = msg.getRequest().getLogin().getPassword();

        System.out.println( "uname " + uname + " password " + password );


        MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
        builder.setType( MsgProtocol.MSG.Login_Response );
        builder.setSequence( msg.getSequence() );

        MsgProtocol.Response.Builder builder1 = MsgProtocol.Response.newBuilder();
        MsgProtocol.LoginResponse.Builder responseBuilder = MsgProtocol.LoginResponse.newBuilder();
        responseBuilder.setRet( 999 );
        builder1.setResult( true );
        builder1.setLogin( responseBuilder.build() );
        builder.setResponse( builder1.build() );

        ctx.writeAndFlush( builder.build() );

    }
}
