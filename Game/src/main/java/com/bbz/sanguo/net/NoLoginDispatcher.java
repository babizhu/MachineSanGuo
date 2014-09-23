package com.bbz.sanguo.net;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.net.handler.HandlerManager;
import com.bbz.sanguo.net.handler.INoLoginHandler;
import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.bbz.sanguo.net.protobuf.MsgProtocol.Message;

/**
 * user         LIUKUN
 * time         2014-9-19 16:36
 * 玩家未登录的时候发送的包在这里进行处理
 */

public class NoLoginDispatcher extends SimpleChannelInboundHandler<Message>{

    MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
    MsgProtocol.Response.Builder responseBuilder = MsgProtocol.Response.newBuilder();

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Message msg ) throws Exception{



        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );
        INoLoginHandler handler = HandlerManager.INSTANCE.getHandlerWithoutUser( msg.getType() );

        if( handler == null ) {

            reportError( new ClientException( ErrorCode.NOT_LOGIN ) );
        }

        else {
            try {
                handler.run( msg.getRequest(), responseBuilder, ctx );

            } catch( ClientException excpetion ){
                reportError( excpetion );
            } catch( Exception e ) {
                e.printStackTrace();

            }
            responseBuilder.setResultCode(0);//明确表示此次调用成功


        }
        builder.setResponse( responseBuilder );
        ctx.writeAndFlush( builder.build() );

        System.out.println( msg.getType() + "(" + msg.getSequence() + "):" +
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
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause:" + cause );
    }
}

