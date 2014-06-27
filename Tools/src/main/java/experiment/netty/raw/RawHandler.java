package experiment.netty.raw;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-6-4 15:42
 */

public class RawHandler extends ChannelHandlerAdapter{

    Channel channel;

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
//        System.out.println( "RawHandler.channelRead" );
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes( bytes );
//        System.out.println( bytes[0] );
        System.out.println( new String( bytes ) );


        //buf.release();

        //ctx.close();
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() );
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeByte( 97 );
        buffer.writeBytes( "you are the one!!!!!!!,wow kakakakka".getBytes() );
//        channel.writeAndFlush( "dsdsada" );
        ctx.writeAndFlush( buffer );
    }

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ) throws Exception{
        channel = ctx.pipeline().channel();

    }


    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + "disconnect!" );
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        System.out.println( "cause " + cause );
    }
}
