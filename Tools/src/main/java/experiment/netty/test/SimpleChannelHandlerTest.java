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
 * time         2014-6-10 16:50
 * 多个SimpleChannelInboundHandler加入到pipeline中，如果同时都overload了inactive方法，会不会都调用？
 * 缺省情况下不会调用，除非手工调用相应的ctx.fireChannelInactive()方法
 */

public class SimpleChannelHandlerTest{

    public static void main( String[] args ){
        new SimpleChannelHandlerTest().run();
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
                pipeline.addLast( new Handler3() );
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

    private static class Handler1 extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( "Handler1.messageReceived:" + msg );
            ctx.fireChannelRead( msg );
        }

        @Override
        public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
            System.out.println( "Handler1.channelInactive" );
            ctx.fireChannelInactive();
            ctx.fireChannelRead( "byte" );
        }
    }

    private static class Handler2 extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( "Handler2.messageReceived:" + msg );
        }

        @Override
        public void channelInactive( ChannelHandlerContext ctx ) throws Exception{
            System.out.println( "Handler2.channelInactive" );
        }

        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( "Handler2.channelRead : " + msg );
        }
    }

    private static class Handler3 extends ChannelHandlerAdapter{
        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( "Handler3.channelRead" );
            super.channelRead( ctx, msg );
        }
    }
}
