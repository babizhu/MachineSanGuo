package experiment.netty.protobuf;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import static experiment.protocolgen.WorldClockProtocol.LocalTimes;

/**
 * user         LIUKUN
 * time         2014/5/25 0025 14:28
 */

public class WorldClockClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast( new ProtobufVarint32FrameDecoder() );
        pipeline.addLast( new ProtobufDecoder( LocalTimes.getDefaultInstance() ) );

//        pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
        pipeline.addLast( new ProtobufEncoder() );
        pipeline.addLast( new WorldClockClientHandler() );
    }
}
