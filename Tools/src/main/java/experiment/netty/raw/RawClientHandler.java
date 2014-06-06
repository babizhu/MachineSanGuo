package experiment.netty.raw;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * user         LIUKUN
 * time         2014-6-5 15:23
 */

public class RawClientHandler extends ChannelHandlerAdapter{
    Channel channel;

    void sendMsg(){
        ByteBuf buf = channel.alloc().buffer();
        buf.writeBytes( "test1".getBytes() );
        channel.writeAndFlush( buf ).addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
                System.out.println( future.isSuccess() );
                System.out.println( future.cause() );
            }
        } );
        System.out.println( "RawClientHandler.sendMsg" );
    }

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ) throws Exception{
        channel = ctx.channel();
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes( bytes );
        System.out.println( new String( bytes ) );

    }
}
