package com.bbz.sanguo.net;

import com.bbz.sanguo.net.protobuf.MsgProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * user         LIUKUN
 * time         2014-9-19 16:35
 */

public class GameServerInitializer extends ChannelInitializer<SocketChannel>{
//    private static final ProtobufVarint32FrameDecoder VARINT32_FRAME_DECODER = new ProtobufVarint32FrameDecoder();
    private static final ProtobufDecoder DECODER = new ProtobufDecoder( MsgProtocol.Request.getDefaultInstance() );
    private static final ProtobufVarint32LengthFieldPrepender VARINT_32_LENGTH_FIELD_PREPENDER = new ProtobufVarint32LengthFieldPrepender();
    private static final ProtobufEncoder ENCODER = new ProtobufEncoder();

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( new ProtobufVarint32FrameDecoder()  );
        pipeline.addLast( DECODER );

        pipeline.addLast( VARINT_32_LENGTH_FIELD_PREPENDER );
        pipeline.addLast( ENCODER );

        pipeline.addLast( new NoLoginDispatcher() );
    }
}
