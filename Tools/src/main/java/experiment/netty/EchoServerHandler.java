package experiment.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * user         LIUKUN
 * time         2014-5-7 11:39
 */

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter{
    private static final Logger logger = Logger.getLogger(
            EchoServerHandler.class.getName() );
    static AtomicInteger counter = new AtomicInteger( 0 );

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
        ByteBuf in = (ByteBuf) msg;
        in.markReaderIndex();

//            System.out.println( in.readInt() );
//            byte[] str = new byte[50];
//            in.readBytes( str );
//            System.out.println( new String (str));
//            in.readBytes( str );
//            System.out.println( new String (str));


        in.resetReaderIndex();

        ctx.writeAndFlush( msg );

//        System.out.println( in.toString( CharsetUtil.US_ASCII ) );

//
//        while( in.isReadable() ){
//            System.out.println( in.readableBytes() );
//            in.readByte();
//            System.out.println( in.readableBytes() );
//            in.writeByte( 2 );
//            System.out.println( "写了之后" + in.readableBytes() );
//            byte b = in.readByte();
//            System.out.println( "b=" + b );
//            System.out.println( "写了之后" + in.readableBytes() );
//        }

    }


    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ){
        // Close the connection when an exception is raised.
        logger.log( Level.WARNING, "Unexpected exception from downstream.", cause );
        ChannelFuture close = ctx.close();
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{

        System.out.println( "RemoteAddress : " + ctx.channel().remoteAddress() + " active !" );
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes( ("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n").getBytes() );
        //ctx.writeAndFlush( buffer );

//        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n" );
//        ctx.write( 65 );
        super.channelActive( ctx );
    }


}
