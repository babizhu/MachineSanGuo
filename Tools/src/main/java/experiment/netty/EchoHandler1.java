package experiment.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * user         LIUKUN
 * time         2014-9-30 17:58
 */

public class EchoHandler1 extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        super.channelRead( ctx, msg );
        System.out.println( "EchoHandler1.channelRead, msg " + msg );
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( "EchoHandler1.channelActive ,ctx=" + ctx );
    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( "EchoHandler1.channelInactive, ctx:" + ctx );
    }

    @Override
    public void disconnect( ChannelHandlerContext ctx, ChannelPromise promise ) throws Exception{
        System.out.println( "EchoHandler1.disconnect,ctx=" + ctx );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "EchoHandler1.exceptionCaught" );
        System.err.println( cause );
        super.exceptionCaught( ctx, cause );
    }
}
