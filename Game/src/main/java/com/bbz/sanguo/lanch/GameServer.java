package com.bbz.sanguo.lanch;

import com.bbz.sanguo.cfg.CfgInit;
import com.bbz.sanguo.net.GameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-7 12:05
 * 服务器启动类
 */

public class GameServer{
    static Logger logger = LoggerFactory.getLogger( GameServer.class );
    private IServer server;

    public GameServer() throws IOException{
        GameHandler handler = new GameHandler();

        server = new Server( ServerCfg.IP, ServerCfg.PORT, handler );
    }

    public void start() throws IOException{
        logger.info( "服务器[{}]开始启动", ServerCfg.SERVER_ID );
        logger.info( "服务器区id：\t\t{}", ServerCfg.SERVER_ID );
        logger.info( "服务器ip：\t\t{}", ServerCfg.IP );
        logger.info( "服务器监听端口：\t{}", ServerCfg.PORT );
        logger.info( "服务器管理端口：\t{}", ServerCfg.GM_PORT );
        CfgInit.init();
        logger.info( "服务器[{}]配置文件读取完毕......", ServerCfg.SERVER_ID );
        server.start();
        logger.info( "服务器[{}]启动完毕......", ServerCfg.SERVER_ID );

    }
}
