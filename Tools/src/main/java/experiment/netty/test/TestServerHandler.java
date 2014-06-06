package experiment.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-5-23 11:42
 */

public class TestServerHandler extends SimpleChannelInboundHandler<Object>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Object msg ) throws Exception{
        System.out.println( msg );
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
//        ByteBuf buf = ctx.alloc().buffer().writeInt( SystemTimer.currentTimeSecond() );
        //ctx.writeAndFlush( buf ).addListener( ChannelFutureListener.CLOSE );
        System.out.println( ctx.channel().remoteAddress() + " connect" );

    }

    @Override
    public void channelRead( final ChannelHandlerContext ctx, Object msg ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " read" );
        ctx.close();

//        System.out.println( 23 );
//        ByteBuf buffer = ctx.alloc().buffer();
//        buffer.writeInt( SystemTimer.currentTimeSecond() );
//
//        ChannelFuture channelFuture = ctx.writeAndFlush( buffer );
//        channelFuture.addListener( ChannelFutureListener.CLOSE );
////        channelFuture.addListener( new ChannelFutureListener(){
////            @Override
////            public void operationComplete( ChannelFuture future ) throws Exception{
////                ctx.close();
////            }
////        } );

    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " disconnect" );
    }
}
