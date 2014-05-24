package experiment.netty.lingkong.client;

import experiment.netty.lingkong.server.RequestEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-23 15:58
 */

public class LingkongClient{

    public LingkongClient(){

    }

    public ChannelFuture connect(){
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group( worker );
            bootstrap.channel( NioSocketChannel.class );
            bootstrap.handler( new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel( SocketChannel ch ) throws Exception{
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast( new RequestEncoder() );
                }
            } );

            ChannelFuture future = bootstrap.connect( "localhost", 8000 ).sync();
            //future.channel().writeAndFlush(  )
            return future;
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }

        return null;
    }

//    public write


}
