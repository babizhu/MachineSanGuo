package com.bbz.sanguo.net;

import com.bbz.sanguo.lanch.ServerCfg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * user         LIUKUN
 * time         2014-9-19 16:28
 * 服务器主类
 */

public class GameServer{

    public static void main( String[] args ){
        new GameServer().run();
    }

    public void run(){


        EventLoopGroup bossGroup = new NioEventLoopGroup( 1 );
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group( bossGroup, workerGroup )
                    .channel( NioServerSocketChannel.class )
                    .childHandler( new GameServerInitializer() )
                    .option( ChannelOption.SO_BACKLOG, 128 )

                    .childOption( ChannelOption.SO_KEEPALIVE, true );

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind( ServerCfg.PORT ).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
