package experiment.netty.socksproxy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.handler.codec.socks.SocksMessageEncoder;

/**
 * user         LIUKUN
 * time         2014-5-24 14:36
 */

public class SocksServerInitializer extends ChannelInitializer<SocketChannel>{
    private final SocksMessageEncoder socksMessageEncoder = new SocksMessageEncoder();
    private final SocksServerHandler socksServerHandler = new SocksServerHandler();

    @Override
    protected void initChannel( SocketChannel ch ) throws Exception{
        ChannelPipeline p = ch.pipeline();
        p.addLast( new SocksInitRequestDecoder() );
        p.addLast( socksMessageEncoder );
        p.addLast( socksServerHandler );

    }
}
