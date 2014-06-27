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
        System.out.println( msg.getType() + " " + builder );
        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );
        IHandlerWithoutUser handler = HandlerManager.INSTANCE.getHandlerWithoutUser( msg.getType() );

        if( handler == null ) {
            reportError();
        }

        try {
            handler.run( msg.getRequest(), responseBuilder, ctx );
            responseBuilder.setResult( true );

        } catch( Exception e ) {
            reportError();
            //e.printStackTrace();

        }

        builder.setResponse( responseBuilder );
        ctx.writeAndFlush( builder.build() );

    }

    /**
     * 报告客户端，后台执行出现异常
     */
    private void reportError(){
        responseBuilder.setResult( false );
        responseBuilder.setErrorDescription( "error" );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause:" + cause );
    }
}
