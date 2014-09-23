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

public class GameClientHandler extends SimpleChannelInboundHandler<MsgProtocol.Response>{
    private final BlockingQueue<MsgProtocol.Response> answer = new LinkedBlockingQueue<>();
    private volatile Channel channel;
    private int sequence = 0;

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, MsgProtocol.Response response ) throws Exception{
        answer.add( response );
    }

    private MsgProtocol.Request.Builder createRequestBuilder( MsgProtocol.MSG msg ){
        MsgProtocol.Request.Builder builder = MsgProtocol.Request.newBuilder();
        builder.setType( msg );
        builder.setSequence( sequence++ );
        return builder;
    }

    MsgProtocol.Response waitResult(){
        boolean interrupted = false;
        MsgProtocol.Response response;
        for(; ; ) {
            try {
//                System.out.println( "missionShow" );
                response = answer.take();
                System.out.println( "结果码 :" + ErrorCode.fromNum( response.getResultCode() ));
                System.out.println( "Sequence :" + response.getSequence() );
                break;
            } catch( InterruptedException ignore ) {
                interrupted = true;
            }
        }

        if( interrupted ) {
            Thread.currentThread().interrupt();
        }
        return response;

    }



    public int missionShow(){


        MsgProtocol.Request.Builder builder = createRequestBuilder( MsgProtocol.MSG.MissionShow );
        MsgProtocol.MissionShowRequest.Builder reqeustBuilder = MsgProtocol.MissionShowRequest.newBuilder();

        reqeustBuilder.setMissionId( 5 );
        builder.setMissionShow( reqeustBuilder.build() );


//        System.out.println( "channel.isOpen() " + channel.isOpen() );
        channel.writeAndFlush( builder ).addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
//                System.out.println( future.isSuccess() );
//                System.out.println( future.cause() );
            }
        } );

        MsgProtocol.Response response = waitResult();
        return response.getResultCode();


    }

    public int login( String uname, String password ){
        MsgProtocol.Request.Builder builder = createRequestBuilder( MsgProtocol.MSG.Login );

        MsgProtocol.LoginRequest.Builder reqeustBuilder = MsgProtocol.LoginRequest.newBuilder();
        reqeustBuilder.setUserName( uname );
        reqeustBuilder.setPassword( password );

        builder.setLogin( reqeustBuilder.build() );


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
