package experiment.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * user         LIUKUN
 * time         2014-6-11 17:09
 * <p/>
 * channel.write()和
 * ctx.write()的区别，很遗憾，没做出来
 */

public class PipeLineTest{
    private static class Handler1 extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            if( msg.charAt( 0 ) == 'b' ) {
                ctx.writeAndFlush( msg );
            } else {
                ctx.channel().writeAndFlush( msg );
            }
        }

        @Override
        public void channelRegistered( ChannelHandlerContext ctx ) throws Exception{

            super.channelRegistered( ctx );
        }
    }
}
