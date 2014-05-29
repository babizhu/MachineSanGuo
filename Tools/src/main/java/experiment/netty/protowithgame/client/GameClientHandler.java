package experiment.netty.protowithgame.client;

import experiment.protocolgen.MsgProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static experiment.protocolgen.MsgProtocol.Message;

/**
 * user         LIUKUN
 * time         2014-5-27 19:13
 */

public class GameClientHandler extends SimpleChannelInboundHandler<Message>{
    private final BlockingQueue<Message> answer = new LinkedBlockingQueue<>();
    private volatile Channel channel;

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Message msg ) throws Exception{
        answer.add( msg );
    }

    public int login( String uname, String password ){
        Message.Builder builder = Message.newBuilder();
        builder.setType( MsgProtocol.MSG.Login );
        builder.setSequence( 12121 );

        MsgProtocol.LoginRequest.Builder reqeustBuilder = MsgProtocol.LoginRequest.newBuilder();
        reqeustBuilder.setUsername( uname );
        reqeustBuilder.setPassword( password );

        builder.setRequest( MsgProtocol.Request.newBuilder().setLogin( reqeustBuilder.build() ) );


        channel.writeAndFlush( builder.build() );

        int ret = -1;
        boolean interrupted = false;
        for(; ; ) {
            try {
                Message msg = answer.take();
                ret = msg.getResponse().getLogin().getRet();
                System.out.println( "结果" + msg.getResponse().getResult() );
                System.out.println( "Sequence " + msg.getSequence() );
                break;
            } catch( InterruptedException ignore ) {
                interrupted = true;
            }
        }

        if( interrupted ) {
            Thread.currentThread().interrupt();
        }


        return ret;
    }

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ){
        channel = ctx.channel();
    }

}
