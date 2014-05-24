package experiment.netty.lingkong.server;

import com.bbz.tool.common.RandomUtil;
import experiment.netty.lingkong.request.AbstractRequest;
import experiment.netty.lingkong.request.AbstractResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-5-23 17:38
 */

public class LingkongHandler extends SimpleChannelInboundHandler<AbstractRequest>{
    @Override
    protected void messageReceived( ChannelHandlerContext ctx, AbstractRequest msg ) throws Exception{
//        System.out.println( msg );
        AbstractResponse response = new AbstractResponse( true, RandomUtil.getInt( 100 ) );
        ctx.writeAndFlush( response );
    }
}
