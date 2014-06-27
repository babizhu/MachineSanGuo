package experiment.netty.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-6-13 11:44
 */

public class DecoderTest{

    public static void main( String[] args ){
        new DecoderTest().run();
    }

    private void run(){
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
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast( new FrameDecoder() );
                            pipeline.addLast( new IntToStringDecoder() );
                            pipeline.addLast( new Handler() );
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

    private static class FrameDecoder extends ReplayingDecoder<Void>{
        @Override
        protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
            out.add( in.readInt() );
        }
    }

    private static class IntToStringDecoder extends MessageToMessageDecoder<Integer>{
        @Override
        public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception{
            super.channelRead( ctx, msg );
        }

        @Override
        protected void decode( ChannelHandlerContext ctx, Integer msg, List<Object> out ) throws Exception{
            out.add( msg + "" );
        }
    }

    private static class Handler extends SimpleChannelInboundHandler<String>{

        @Override
        protected void messageReceived( ChannelHandlerContext ctx, String msg ) throws Exception{
            System.out.println( msg );
        }
    }
}
