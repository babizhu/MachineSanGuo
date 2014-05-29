package experiment.netty.protowithgame.server;

import experiment.netty.protowithgame.server.handler.AbstractHandler;
import experiment.netty.protowithgame.server.handler.HandlerManager;
import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static experiment.protocolgen.MsgProtocol.Response;

/**
 * user         LIUKUN
 * time         2014-5-27 19:48
 */

public class GameServerHandler extends SimpleChannelInboundHandler<MsgProtocol.Message>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Message msg ) throws Exception{


        MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );

        Response.Builder responseBuilder = Response.newBuilder();
        try {
            AbstractHandler handler = HandlerManager.INSTANCE.getHandler( msg.getType() );
            handler.run( msg.getRequest(), responseBuilder );
            responseBuilder.setResult( true );

        } catch( Exception e ) {
            responseBuilder.setResult( false );
            responseBuilder.setErrorDescription( "error" );
            e.printStackTrace();
        }

        builder.setResponse( responseBuilder );
        ctx.writeAndFlush( builder.build() );

    }
}
