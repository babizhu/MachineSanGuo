package com.bbz.sanguo.lanch;

import com.bbz.sanguo.net.GameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-3 14:38
 * 项目启动类
 */

public class Lanch{

    static Logger logger = LoggerFactory.getLogger( Lanch.class );
    public static void main( String[] args ) throws IOException{

        IServer srv = new Server(8090, new GameHandler());

// run it within the current thread.
//        srv.run();  // the call will not return

// ... or start it by using a dedicated thread
        srv.start(); // returns after the server has been started
        logger.debug( "服务器启动了" );

    }
}
