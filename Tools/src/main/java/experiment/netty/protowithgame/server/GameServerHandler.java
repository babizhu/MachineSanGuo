package experiment.netty.protowithgame.server;

import experiment.netty.protowithgame.server.handler.HandlerManager;
import experiment.netty.protowithgame.server.handler.IHandlerWithUser;
import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-5-27 19:48
 */

public class GameServerHandler extends SimpleChannelInboundHandler<MsgProtocol.Message>{
    MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
    MsgProtocol.Response.Builder responseBuilder = MsgProtocol.Response.newBuilder();
    Object user = null;

    public GameServerHandler(){
        System.out.println( "GameServerHandler.GameServerHandler" );
        ;
    }

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Message msg ) throws Exception{

        System.out.println( msg.getType() );
        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );
        IHandlerWithUser handler = HandlerManager.INSTANCE.getHandlerWithUser( msg.getType() );

        try {
            handler.run( msg.getRequest(), responseBuilder, user );
            responseBuilder.setResult( true );

        } catch( Exception e ) {
            responseBuilder.setResult( false );
            responseBuilder.setErrorDescription( "error" );
            e.printStackTrace();
        }

        builder.setResponse( responseBuilder );
        ctx.writeAndFlush( builder.build() );

    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause:" + cause );
    }
}
