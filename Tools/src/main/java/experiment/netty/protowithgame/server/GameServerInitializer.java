package experiment.netty.protowithgame.server;

import experiment.protocolgen.MsgProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * user         LIUKUN
 * time         2014-5-27 19:46
 */

public class GameServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast( new ProtobufVarint32FrameDecoder() );
        pipeline.addLast( new ProtobufDecoder( MsgProtocol.Message.getDefaultInstance() ) );

        pipeline.addLast( new ProtobufVarint32LengthFieldPrepender() );
        pipeline.addLast( new ProtobufEncoder() );

        pipeline.addLast( new ProcessHandlerWithoutUser() );
    }
}
