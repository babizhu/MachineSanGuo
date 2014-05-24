package experiment.netty.telnet;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Date;

/**
 * user         LIUKUN
 * time         2014-5-8 15:06
 */
@ChannelHandler.Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String>{
    public static final Logger logger = LoggerFactory.getLogger( TelnetServerHandler.class );

    public TelnetServerHandler(){
        System.out.println( getClass() );
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        ctx.write( "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n" );
        ctx.write( "It is " + new Date() + " now.\r\n" );
        ctx.flush();
    }

    @Override
    protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
        String response;
        boolean close = false;
        if( msg.isEmpty() ) {
            response = "Please type something.\r\n";
        } else if( msg.toLowerCase().equals( "bye" ) ) {
            close = true;
            response = "Have a good day!\r\n";
        } else {
            response = "Did you say '" + msg + "'?\r\n";
        }

        ChannelFuture future = ctx.write( response );
        if( close ) {
            future.addListener( ChannelFutureListener.CLOSE );
        }
    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
        System.out.println( ctx.channel().remoteAddress() + " is disconnect!" );
    }

    @Override
    public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception{
        ctx.flush();
    }

    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) throws Exception{
        logger.warn( "Unexpected exception from downstream.", cause );
        ctx.close();
//        ChannelInvoker
    }
}
