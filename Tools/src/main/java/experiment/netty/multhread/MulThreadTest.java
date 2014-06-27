package experiment.netty.multhread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-6-23 16:08
 * 测试多线程同时写，数据会不会乱
 * 结论 数据不会乱
 */

public class MulThreadTest{
    ChannelHandlerContext user;

    public static void main( String[] args ){
        new MulThreadTest().run();
    }

    void run(){
        int port = 8000;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new ChannelInitializer<SocketChannel>(){
                        @Override
                        public void initChannel( SocketChannel ch ) throws Exception{
                            ch.pipeline().addLast( new Handler1() );
                        }
                    } )
                    .option( ChannelOption.SO_BACKLOG, 128 )
                    .childOption( ChannelOption.SO_KEEPALIVE, true );

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind( port ).sync(); // (7)

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

    private final class Worker implements Runnable{

        @Override
        public void run(){
            System.out.println( Thread.currentThread().getName() + ": Worker.run" );
            ByteBuf buffer = user.alloc().buffer();
            buffer.writeBytes( "liu lao ye".getBytes() );
            user.writeAndFlush( buffer );
        }
    }

    private final class Handler1 extends ChannelHandlerAdapter{
        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( Thread.currentThread().getName() + ": Handler1.channelRead" );
            ByteBuf content = (ByteBuf) msg;
            System.out.println( content.getByte( 0 ) );
            ByteBuf buffer = ctx.alloc().buffer();
            buffer.writeBytes( "aabb__".getBytes() );
            //调用另外的线程发包，模拟有其他线程在给客户端写数据
            new Thread( new Worker() ).start();
//            Thread.sleep( 1000 );
            System.out.println( "xxxxxxxxxxx" );
            buffer.writeBytes( content );
            buffer.writeBytes( "__ccdd\r\n".getBytes() );
            //ctx.writeAndFlush( buffer );
        }

        @Override
        public void channelActive( ChannelHandlerContext ctx ) throws Exception{
            System.out.println( Thread.currentThread().getName() + ": Handler1.channelActive" );
            user = ctx;
        }
    }


}

