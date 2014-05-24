package experiment.netty.socksproxy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Promise;

/**
 * user         LIUKUN
 * time         2014-5-24 14:54
 */

public final class DirectClientHandler extends ChannelHandlerAdapter{

    private final Promise<Channel> promise;

    public DirectClientHandler( Promise<Channel> promise ){
        this.promise = promise;
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ){
        ctx.pipeline().remove( this );
        promise.setSuccess( ctx.channel() );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable throwable ){
        promise.setFailure( throwable );
    }
}
