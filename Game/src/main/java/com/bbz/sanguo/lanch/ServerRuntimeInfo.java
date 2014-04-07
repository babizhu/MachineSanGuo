package com.bbz.sanguo.lanch;

import com.bbz.util.time.SystemTimer;
import org.joda.time.DateTime;

/**
 * user         LIUKUN
 * time         2014-4-7 14:25
 * 服务器的运行时信息
 */

public class ServerRuntimeInfo{
    private final int   serverId;
    /**
     * 启动时间,秒为单位
     */
    private final int startTime;

    public ServerRuntimeInfo( int serverId ){
        this.serverId = serverId;
        startTime = SystemTimer.currentTimeSecond();
    }

    /**
     * 格式化启动时间
     * @return 时间字符串
     */
    public String getStartTime(){
        DateTime dateTime = new DateTime( startTime * 1000l );

        return dateTime.toString("yyyy/MM/dd HH:mm:ss");
    }

    public int getServerId(){
        return serverId;
    }

    public static void main( String[] args ){
        ServerRuntimeInfo info = new ServerRuntimeInfo( 10002 );
        System.out.println( info.getServerId() );
        System.out.println( info );
        System.out.println( info.getStartTime() );
    }
}
