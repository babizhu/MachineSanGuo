package experiment.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * user         LIUKUN
 * time         2014-6-10 11:56
 */


public class FireChannelReadTest{
    public static void main( String[] args ){
        new EchoServer().run();
    }

    private static class MyHandler extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( "MyHandler.messageReceived ： " + msg );

//            Thread.sleep( 5000 );
            if( msg.charAt( 0 ) == 'b' ) {
                ChannelPipeline pipeline = ctx.pipeline();
                pipeline.addLast( new MyHandler1() );
                pipeline.remove( this );
                ctx.fireChannelRead( msg );//上面的顺序居然很重要
            }
        }
    }

    static class MyHandler1 extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( "MyHandler1.messageReceived ： " + msg );
        }
    }

    private static class EchoServer{
        public static final int PORT = 8000;

        private void run(){
            EventLoopGroup boss = new NioEventLoopGroup( 1 );
            EventLoopGroup worker = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group( boss, worker );
            bootstrap.channel( NioServerSocketChannel.class );
            bootstrap.childHandler( new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel( SocketChannel ch ) throws Exception{
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast( new StringDecoder( Charset.defaultCharset() ) );
                    pipeline.addLast( new StringEncoder( Charset.defaultCharset() ) );
                    pipeline.addLast( new MyHandler() );
                }
            } );

            try {
                bootstrap.bind( PORT ).sync().channel().closeFuture().sync();
            } catch( InterruptedException e ) {
                e.printStackTrace();
            } finally {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
            }
        }
    }
}
