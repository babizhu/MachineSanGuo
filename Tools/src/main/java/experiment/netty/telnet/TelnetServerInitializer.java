package experiment.netty.telnet;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * user         LIUKUN
 * time         2014-5-8 15:00
 */

public class TelnetServerInitializer extends ChannelInitializer<SocketChannel>{
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final TelnetServerHandler SERVERHANDLER = new TelnetServerHandler();

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast( "framer", new DelimiterBasedFrameDecoder( 8192, Delimiters.lineDelimiter() ) );
        pipeline.addLast( "decoder", DECODER );
        pipeline.addLast( "encoder", ENCODER );
        pipeline.addLast( "handler", SERVERHANDLER );
//        ByteToByteCodec
//        ByteToMessageCodec

    }
}
