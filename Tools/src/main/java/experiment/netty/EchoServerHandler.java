package experiment.netty;

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
        ctx.writeAndFlush( msg );
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception{
//        ctx.flush();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ){
        // Close the connection when an exception is raised.
        logger.log( Level.WARNING, "Unexpected exception from downstream.", cause );
        ctx.close();
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{

        System.out.println( "RemoteAddress : " + ctx.channel().remoteAddress() + " active !" );

        ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n" );
        ctx.write( 65 );
        super.channelActive( ctx );
    }


}
