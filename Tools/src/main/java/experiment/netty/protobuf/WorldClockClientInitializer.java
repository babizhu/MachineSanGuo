package experiment.netty.protobuf;

import experiment.netty.protobuf.mycodec.codec.GameCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 14:28
 */

public class WorldClockClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast( new ProtobufVarint32FrameDecoder() );
//        pipeline.addLast( new ProtobufDecoder( LocalTimes.getDefaultInstance() ) );
//
//        pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
//        pipeline.addLast( new ProtobufEncoder() );

        pipeline.addLast( new GameCodec( false ) );
        pipeline.addLast( new WorldClockClientHandler() );
    }
}
