package experiment.netty.idleconnection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * user         LIUKUN
 * time         2014-5-20 17:13
 */

public class IdleServerInitHandler extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( new IdleStateHandler( 0, 0, 10, TimeUnit.SECONDS ) );
        pipeline.addLast( new HeartbeatHandler() );
    }

    public static final class HeartbeatHandler extends ChannelHandlerAdapter{
        private static final ByteBuf HEARTBEAT_SEQUENCE =
                Unpooled.unreleasableBuffer( Unpooled.copiedBuffer(
                        "HEARTBEAT", CharsetUtil.ISO_8859_1 ) );

        @Override
        public void userEventTriggered( ChannelHandlerContext ctx,
                                        Object evt ) throws Exception{
            System.out.println( evt );
            if( evt instanceof IdleStateEvent ) {
                ctx.writeAndFlush( HEARTBEAT_SEQUENCE.duplicate() )
                        .addListener(
                                ChannelFutureListener.CLOSE_ON_FAILURE );
            } else {
                super.userEventTriggered( ctx, evt );
            }
        }
    }
}