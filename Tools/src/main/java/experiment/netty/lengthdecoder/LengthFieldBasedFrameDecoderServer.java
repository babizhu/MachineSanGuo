package experiment.netty.lengthdecoder;

import experiment.netty.telnet.TelnetServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-10 17:59
 * <p/>
 * 学习LengthFieldBasedFrameDecoderServer的用法
 */

public class LengthFieldBasedFrameDecoderServer{

    private final int port;

    public LengthFieldBasedFrameDecoderServer( int port ){
        this.port = port;
    }

    public static void main( String[] args ) throws Exception{
        int port;
        if( args.length > 0 ) {
            port = Integer.parseInt( args[0] );
        } else {
            port = 8000;
        }
        new LengthFieldBasedFrameDecoderServer( port ).run();


    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup( 1 );
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup ).channel( NioServerSocketChannel.class ).childHandler( new TelnetServerInitializer() );
            b.bind( port ).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

