package experiment.netty.telnet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * user         LIUKUN
 * time         2014-6-4 14:41
 */

public class TelnetClientHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        try {
            System.out.println( buf.toString( Charset.defaultCharset() ) );
            ctx.writeAndFlush( ByteBufUtil.encodeString( ctx.alloc(), CharBuffer.wrap( "abcdefg" ), Charset.defaultCharset() ) );
        } finally {
            buf.release();
        }
    }
}
