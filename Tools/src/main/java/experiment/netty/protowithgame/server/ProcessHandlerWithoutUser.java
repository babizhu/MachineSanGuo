package experiment.netty.protowithgame.server;

import experiment.netty.protowithgame.server.handler.HandlerManager;
import experiment.netty.protowithgame.server.handler.IHandlerWithoutUser;
import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static experiment.protocolgen.MsgProtocol.Message;

/**
 * user         LIUKUN
 * time         2014-6-3 13:56
 */

public class ProcessHandlerWithoutUser extends SimpleChannelInboundHandler<Message>{


    MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
    MsgProtocol.Response.Builder responseBuilder = MsgProtocol.Response.newBuilder();

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Message msg ) throws Exception{
        System.out.println( "GameServerLoginHandler 客户端请求是" + msg.getType() );


        IHandlerWithoutUser handler = HandlerManager.INSTANCE.getHandlerWithoutUser( msg );

//        if( msg.getType() == Login ) {
//            LoginHandler handler = (LoginHandler) HandlerManager.INSTANCE.getHandler( msg.getType() );
//            try {
//                handler.run( msg.getRequest(), responseBuilder, ctx );
//                responseBuilder.setResult( true );
//            } catch( Exception e ) {
//                responseBuilder.setResult( false );
//                responseBuilder.setErrorDescription( "error" );
//                e.printStackTrace();
//            }
//        }
//        else{
//
//        }
//        AbstractHandler


        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );
        builder.setResponse( responseBuilder );
        ctx.writeAndFlush( builder.build() );
//
//        ChannelPipeline pipeline = ctx.pipeline();
//        pipeline.addLast( new GameServerHandler() );
//        pipeline.remove( this );
    }
}
