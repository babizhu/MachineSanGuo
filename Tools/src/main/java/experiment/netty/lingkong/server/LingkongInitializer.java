package experiment.netty.lingkong.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * user         LIUKUN
 * time         2014-5-23 17:27
 */

public class LingkongInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( new RequestDecoder() );
        pipeline.addLast( new RequestEncoder() );
        pipeline.addLast( new LingkongHandler() );

    }
}
