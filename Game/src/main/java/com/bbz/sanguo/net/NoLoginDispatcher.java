package com.bbz.sanguo.net;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.net.handler.HandlerManager;
import com.bbz.sanguo.net.handler.INoLoginHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.bbz.sanguo.net.protobuf.MsgProtocol.Request;
import static com.bbz.sanguo.net.protobuf.MsgProtocol.Response;

/**
 * user         LIUKUN
 * time         2014-9-19 16:36
 * 玩家未登录的时候发送的包在这里进行处理
 * 每个连接会new一个此类的实例
 */

public class NoLoginDispatcher extends SimpleChannelInboundHandler<Request>{

    Response.Builder responseBuilder = Response.newBuilder();



    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Request request ) throws Exception{

        responseBuilder.setType( request.getType() );
        responseBuilder.setSequence( request.getSequence() );
        INoLoginHandler handler = HandlerManager.INSTANCE.getHandlerWithoutUser( request.getType() );

        if( handler == null ) {
            reportError( new ClientException( ErrorCode.NOT_LOGIN ) );
        } else {
            try {
                handler.run( request, responseBuilder, ctx );

            } catch( ClientException excpetion ) {
                reportError( excpetion );
            } catch( Exception e ) {
                e.printStackTrace();

            }
            responseBuilder.setResultCode( 0 );//明确表示此次调用成功
        }
        ctx.writeAndFlush( responseBuilder.build() );

        System.out.println( responseBuilder.getType() + "(" + responseBuilder.getSequence() + "):" +
                ErrorCode.fromNum( responseBuilder.getResultCode() ) +
                " [" + Thread.currentThread().getName() + "]" );

    }

    /**
     * 报告客户端，服务端执行出现异常
     */
    private void reportError( ClientException exception ){
        responseBuilder.setResultCode( exception.getCode().toNum() );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause:" + cause );
        ChannelFuture close = ctx.close();
    }
}

