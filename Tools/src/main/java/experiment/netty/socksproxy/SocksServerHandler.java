package experiment.netty.socksproxy;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.socks.*;

/**
 * user         LIUKUN
 * time         2014-5-24 14:40
 */

@ChannelHandler.Sharable
public final class SocksServerHandler extends SimpleChannelInboundHandler<SocksRequest>{

    @Override
    public void messageReceived( ChannelHandlerContext ctx, SocksRequest socksRequest ){

        switch( socksRequest.requestType() ) {
            case INIT: {
                // auth support example
                //ctx.pipeline().addFirst(new SocksAuthRequestDecoder());
                //ctx.write(new SocksInitResponse(SocksAuthScheme.AUTH_PASSWORD));
                ctx.pipeline().addFirst( new SocksCmdRequestDecoder() );
                ctx.write( new SocksInitResponse( SocksAuthScheme.NO_AUTH ) );
                break;
            }
            case AUTH:
                ctx.pipeline().addFirst( new SocksCmdRequestDecoder() );
                ctx.write( new SocksAuthResponse( SocksAuthStatus.SUCCESS ) );
                break;
            case CMD:
                SocksCmdRequest req = (SocksCmdRequest) socksRequest;
                if( req.cmdType() == SocksCmdType.CONNECT ) {
                    ctx.pipeline().addLast( new SocksServerConnectHandler() );
                    ctx.pipeline().remove( this );
                    ctx.fireChannelRead( socksRequest );
                } else {
                    ctx.close();
                }
                break;
            case UNKNOWN:
                ctx.close();
                break;
        }
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ){
        ctx.flush();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable throwable ){
        throwable.printStackTrace();
        SocksServerUtils.closeOnFlush( ctx.channel() );
    }
}