package experiment.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * user         LIUKUN
 * time         2014-6-11 11:59
 */

public class HandlerTest{
    public static void main( String[] args ){
        new HandlerTest().run();
    }

    private void run(){
        int port = 8000;
        EventLoopGroup boss = new NioEventLoopGroup( 1 );
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group( boss, worker );
        bootstrap.channel( NioServerSocketChannel.class );
        bootstrap.childHandler( new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel( SocketChannel ch ) throws Exception{
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast( new StringDecoder() );
                pipeline.addLast( new StringEncoder() );
                pipeline.addLast( new Handler1() );
                pipeline.addLast( new Handler2() );
            }
        } );

        try {
            bootstrap.bind( port ).sync().channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    private static class Handler1 extends ChannelHandlerAdapter{
        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( "Handler1.channelRead" );
            super.channelRead( ctx, msg );
//            ObjectHandler a = new
            System.out.println( "xxxxxxxxxxxxxxxxx" );
//            ctx.fireChannelRead( msg );
        }
    }

    private static class Handler2 extends ChannelHandlerAdapter{
        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( "Handler2.channelRead" );
            super.channelRead( ctx, msg );
        }
    }

}
