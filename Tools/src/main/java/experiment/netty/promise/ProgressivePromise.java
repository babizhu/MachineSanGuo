package experiment.netty.promise;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * user         LIUKUN
 * time         2014-7-24 16:43
 * 测试一下一个耗时操作完成之后的回调工作
 */

public class ProgressivePromise{

    public static final int PORT = 8000;

    public static void main( String[] args ){
        new ProgressivePromise().start();
    }

    /**
     * 某种耗时操作
     */
    void future1( ChannelHandlerContext ctx ){
        ctx.writeAndFlush( "test" ).addListener( new ChannelFutureListener(){
            @Override
            public void operationComplete( ChannelFuture future ) throws Exception{
                System.out.println( "这是完成后通知一次的一锤子买卖" );

            }
        } );
    }

    void future2( ChannelHandlerContext ctx ){
        ChannelFuture future = ctx.writeAndFlush( "future2", ctx.newProgressivePromise() );
        future.addListener( new ChannelProgressiveFutureListener(){

            @Override
            public void operationProgressed( ChannelProgressiveFuture future, long progress, long total ) throws Exception{
                if( total < 0 ) { // total unknown
                    System.err.println( future.channel() + " Transfer progress: " + progress );
                } else {
                    System.err.println( future.channel() + " Transfer progress: " + progress + " / " + total );
                }
            }

            @Override
            public void operationComplete( ChannelProgressiveFuture future ) throws Exception{
                System.out.println( "这future2是完成后通知一次的一锤子买卖" );
            }
        } );
    }

    void start(){

        EventLoopGroup bossGroup = new NioEventLoopGroup( 1 );
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new ChannelInitializer<SocketChannel>(){
                        @Override
                        public void initChannel( SocketChannel ch ) throws Exception{
                            ch.pipeline().addLast( new StringDecoder() );
                            ch.pipeline().addLast( new StringEncoder() );
                            ch.pipeline().addLast( new Handler1() );
                        }
                    } )
                    .option( ChannelOption.SO_BACKLOG, 128 )
                    .childOption( ChannelOption.SO_KEEPALIVE, true );

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind( PORT ).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    class Handler1 extends SimpleChannelInboundHandler<String>{

        boolean off;

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( msg );
            if( off ) {
                future1( ctx );
            } else {
                future2( ctx );
            }
            off = !off;
        }

    }


}
