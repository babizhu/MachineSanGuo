package com.bbz.sanguo.net;

import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * user         LIUKUN
 * time         2014-5-27 19:13
 */

public class GameClientHandler extends SimpleChannelInboundHandler<MsgProtocol.Message>{
    private final BlockingQueue<MsgProtocol.Message> answer = new LinkedBlockingQueue<>();
    private volatile Channel channel;
    private int sequence = 0;

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Message msg ) throws Exception{
        answer.add( msg );
    }

    private MsgProtocol.Message.Builder createMsgBuilder( MsgProtocol.MSG msg){
        MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
        builder.setType( msg );
        builder.setSequence( sequence++ );
        return builder;
    }

    MsgProtocol.Response waitResult(){
        boolean interrupted = false;
        MsgProtocol.Message msg = null;
        for(; ; ) {
            try {
//                System.out.println( "missionShow" );
                msg = answer.take();
                System.out.println( "结果码 :" + ErrorCode.fromNum( msg.getResponse().getResultCode() ));
                System.out.println( "Sequence :" + msg.getSequence() );
                break;
            } catch( InterruptedException ignore ) {
                interrupted = true;
            }
        }

        if( interrupted ) {
            Thread.currentThread().interrupt();
        }
        return msg.getResponse();

    }



    public void missionShow(){


        MsgProtocol.Message.Builder builder = createMsgBuilder( MsgProtocol.MSG.MissionShow );
        MsgProtocol.MissionShowRequest.Builder reqeustBuilder = MsgProtocol.MissionShowRequest.newBuilder();

        reqeustBuilder.setMissionId( 5 );
        builder.setRequest( MsgProtocol.Request.newBuilder().setMissionShow( reqeustBuilder.build() ) );


//        System.out.println( "channel.isOpen() " + channel.isOpen() );
        channel.writeAndFlush( builder ).addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
//                System.out.println( future.isSuccess() );
//                System.out.println( future.cause() );
            }
        } );

        MsgProtocol.Response response = waitResult();

    }

    public int login( String uname, String password ){
        MsgProtocol.Message.Builder builder = createMsgBuilder( MsgProtocol.MSG.Login );

        MsgProtocol.LoginRequest.Builder reqeustBuilder = MsgProtocol.LoginRequest.newBuilder();
        reqeustBuilder.setUserName( uname );
        reqeustBuilder.setPassword( password );

        builder.setRequest( MsgProtocol.Request.newBuilder().setLogin( reqeustBuilder.build() ) );


        channel.writeAndFlush( builder );
        MsgProtocol.Response response = waitResult();
        return response.getLogin().getRet();
    }

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ){
        channel = ctx.channel();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        super.exceptionCaught( ctx, cause );
        cause.printStackTrace();
    }
}
