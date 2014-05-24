package experiment.netty.protobuf;

import com.google.protobuf.MessageLite;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * user         LIUKUN
 * time         2014-5-21 17:24
 */

public class ProtobufInitializer extends ChannelInitializer<SocketChannel>{
//    private final MessageLite lite;

    public ProtobufInitializer( MessageLite lite ){
//        this.lite = lite;
//        lite.
    }

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( new ProtobufVarint32FrameDecoder() );
        pipeline.addLast( new ProtobufEncoder() );
        pipeline.addLast( new ProtobufDecoder( Message.MessageInfo.getDefaultInstance() ) );
        pipeline.addLast( new ObjectHandler() );
    }

    private class ObjectHandler extends SimpleChannelInboundHandler<Object>{
        @Override
        protected void messageReceived( ChannelHandlerContext ctx, Object msg ) throws Exception{
            System.out.println( msg.getClass() );
            System.out.println( msg );
        }
    }
}
