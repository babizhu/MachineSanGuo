package experiment.netty.socksproxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socks.SocksCmdRequest;
import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.handler.codec.socks.SocksCmdStatus;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

/**
 * user         LIUKUN
 * time         2014-5-24 14:52
 */

@ChannelHandler.Sharable
public final class SocksServerConnectHandler extends SimpleChannelInboundHandler<SocksCmdRequest>{

    private final Bootstrap b = new Bootstrap();

    @Override
    public void messageReceived( final ChannelHandlerContext ctx, final SocksCmdRequest request ) throws Exception{
        Promise<Channel> promise = ctx.executor().newPromise();
        promise.addListener(
                new GenericFutureListener<Future<Channel>>(){
                    @Override
                    public void operationComplete( final Future<Channel> future ) throws Exception{
                        final Channel outboundChannel = future.getNow();
                        if( future.isSuccess() ) {
                            ctx.channel().writeAndFlush( new SocksCmdResponse( SocksCmdStatus.SUCCESS, request.addressType() ) )
                                    .addListener( new ChannelFutureListener(){
                                        @Override
                                        public void operationComplete( ChannelFuture channelFuture ){
                                            ctx.pipeline().remove( SocksServerConnectHandler.this );
                                            outboundChannel.pipeline().addLast( new RelayHandler( ctx.channel() ) );
                                            ctx.pipeline().addLast( new RelayHandler( outboundChannel ) );
                                        }
                                    } );
                        } else {
                            ctx.channel().writeAndFlush( new SocksCmdResponse( SocksCmdStatus.FAILURE, request.addressType() ) );
                            SocksServerUtils.closeOnFlush( ctx.channel() );
                        }
                    }
                }
        );

        final Channel inboundChannel = ctx.channel();

        b.group( inboundChannel.eventLoop() )
                .channel( NioSocketChannel.class )
                .option( ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000 )
                .option( ChannelOption.SO_KEEPALIVE, true )
                .handler( new DirectClientHandler( promise ) );

        b.connect( request.host(), request.port() ).addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
                if( future.isSuccess() ) {
                    // Connection established use handler provided results
                } else {
                    // Close the connection if the connection attempt has failed.
                    ctx.channel().writeAndFlush(
                            new SocksCmdResponse( SocksCmdStatus.FAILURE, request.addressType() ) );
                    SocksServerUtils.closeOnFlush( ctx.channel() );
                }
            }
        } );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        SocksServerUtils.closeOnFlush( ctx.channel() );
    }
}