package com.bbz.sanguo.lanch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * user         LIUKUN
 * time         2014-4-7 13:38
 *
 * 服务器的整体配置文件
 *
 */

public class ServerCfg{

    /**
     * 监听ip
     */
    public static final String     ip;

    /**
     * 监听端口
     */
    public static int              port;

    /**
     * 管理端口
     */
    public static int              gmPort;

    /**
     * 游戏区
     */
    public static int              serverId;

    static {
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream( new FileInputStream( "resource/server.properties" ) );
            prop.load( in );

        } catch( IOException e ) {
            e.printStackTrace();
        }

        ip = prop.getProperty( "ip" ).trim();
        port = Integer.parseInt( prop.getProperty( "port" ).trim() );
        gmPort = Integer.parseInt( prop.getProperty( "gmPort" ).trim() );
        serverId = Integer.parseInt( prop.getProperty( "serverId" ).trim() );
    }

}

