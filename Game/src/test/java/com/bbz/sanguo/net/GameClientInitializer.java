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
 * time         2014-5-27 18:28
 */

public class GameClientInitializer extends ChannelInitializer<SocketChannel>{

    private ProtobufDecoder decoder = new ProtobufDecoder( MsgProtocol.Message.getDefaultInstance() );
    private ProtobufVarint32LengthFieldPrepender prepender = new ProtobufVarint32LengthFieldPrepender();
    private ProtobufEncoder encoder = new ProtobufEncoder();

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast( new ProtobufVarint32FrameDecoder() );
        pipeline.addLast( decoder );
        pipeline.addLast( prepender );
        pipeline.addLast( encoder );
        pipeline.addLast( new GameClientHandler() );
    }
}
