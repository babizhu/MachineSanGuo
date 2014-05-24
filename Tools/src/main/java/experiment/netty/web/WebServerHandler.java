package experiment.netty.web;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * user         LIUKUN
 * time         2014-5-20 16:48
 */

public class WebServerHandler extends SimpleChannelInboundHandler<Object>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, Object msg ) throws Exception{
//        System.out.println( msg );
        System.out.println( msg.getClass() + " " + ctx.channel().remoteAddress() );
        FullHttpResponse response = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.copiedBuffer( "HELLO".getBytes() ) );
        ctx.writeAndFlush( "abcd" );
        DefaultHttpRequest request = null;
        LastHttpContent lastHttpContent = null;
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " connected" );
    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " disConnected" );
    }


}
