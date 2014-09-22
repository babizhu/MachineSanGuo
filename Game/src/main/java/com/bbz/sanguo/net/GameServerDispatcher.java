package com.bbz.sanguo.net;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.net.handler.HandlerManager;
import com.bbz.sanguo.net.handler.IGameHandler;
import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.bbz.sanguo.net.protobuf.MsgProtocol.Message;

/**
 * user         LIUKUN
 * time         2014-9-19 16:36
 * 玩家登录之后的处理逻辑
 */

public class GameServerDispatcher extends SimpleChannelInboundHandler<Message>{

    MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
    MsgProtocol.Response.Builder responseBuilder = MsgProtocol.Response.newBuilder();

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Message msg ) throws Exception{

        builder.setType( msg.getType() );
        builder.setSequence( msg.getSequence() );
        IGameHandler handler = HandlerManager.INSTANCE.getHandlerWithUser( msg.getType() );

        if( handler == null ) {
            System.err.println( msg.getType()+"句柄没找到" );
            reportError( new ClientException( ErrorCode.HAS_LOGIN ));
        } else {
            try {
                handler.run( msg.getRequest(), responseBuilder, ctx );
//                responseBuilder.setResult( true );

            }catch( ClientException exception ){
                reportError( exception );
            }
            catch( Exception e ) {
                e.printStackTrace();

            }

            builder.setResponse( responseBuilder );
            ctx.writeAndFlush( builder.build() );

            System.out.println( msg.getType() + "句柄被调用了" );
        }
    }

    /**
     * 报告客户端，后台执行出现异常
     */
    private void reportError(ClientException exception){
        responseBuilder.setResultCode( exception.getCode().toNum() );
    }



    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        super.channelInactive( ctx );
        System.out.println( ctx.channel().remoteAddress() + " GameServerDispatcher.channelInactive" +  " 断开了连接");
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause1:" + cause );
    }
}
