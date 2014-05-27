package experiment.netty.protowithgame.client;

import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * user         LIUKUN
 * time         2014-5-27 18:28
 */

public class GameClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( new ProtobufDecoder( MsgProtocol.Message.getDefaultInstance() ) );

//        pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
        pipeline.addLast( new ProtobufEncoder() );
        pipeline.addLast( new GameClientHandler() );
    }
}
