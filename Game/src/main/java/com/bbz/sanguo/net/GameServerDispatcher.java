package com.bbz.sanguo.net;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.net.handler.HandlerManager;
import com.bbz.sanguo.net.handler.IGameHandler;
import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-9-19 16:36
 * 玩家登录之后的处理逻辑
 */

public class GameServerDispatcher extends SimpleChannelInboundHandler<MsgProtocol.Request>{

//    MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
    MsgProtocol.Response.Builder responseBuilder = MsgProtocol.Response.newBuilder();

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Request request ) throws Exception{

        responseBuilder.setType( request.getType() );
        responseBuilder.setSequence( request.getSequence() );
        IGameHandler handler = HandlerManager.INSTANCE.getHandlerWithUser( request.getType() );

        if( handler == null ) {
            reportError( new ClientException( ErrorCode.HAS_LOGIN ) );
        } else {
            try {
                handler.run( request, responseBuilder, ctx );
//                responseBuilder.setResult( true );

            } catch( ClientException exception ) {
                reportError( exception );
            } catch( Exception e ) {
                e.printStackTrace();

            }
            responseBuilder.setResultCode(0);//明确表示此次调用成功


        }
        ctx.writeAndFlush( responseBuilder.build() );

        System.out.println( responseBuilder.getType() + "(" + responseBuilder.getSequence() + "):" +
                ErrorCode.fromNum( responseBuilder.getResultCode() ) +
                " ["+Thread.currentThread().getName()+"]");


    }

    /**
     * 报告客户端，后台执行出现异常
     */
    private void reportError( ClientException exception ){
        responseBuilder.setResultCode( exception.getCode().toNum() );
    }


    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        super.channelInactive( ctx );
        System.out.println( ctx.channel().remoteAddress() + " GameServerDispatcher.channelInactive" + " 断开了连接" );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause1:" + cause );
    }
}
