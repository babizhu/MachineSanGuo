package experiment.netty.web;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * user         LIUKUN
 * time         2014-5-20 16:16
 */

public class WebServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast( "decoder", new HttpRequestDecoder() );
//        pipeline.addLast( "encoder", new HttpRequestEncoder() );
        pipeline.addLast( "codec", new HttpServerCodec() );//1个codec搞定所有
//        pipeline.addLast( "aggegator", new HttpObjectAggregator( 512 * 1024 ) );
        pipeline.addLast( "handler", new WebServerHandler() );

    }
}
