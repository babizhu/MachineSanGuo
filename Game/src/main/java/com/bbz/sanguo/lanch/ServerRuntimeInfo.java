package com.bbz.sanguo.lanch;

import com.bbz.sanguo.db.RunTimeDataProvider;
import com.bbz.tool.time.SystemTimer;
import com.bbz.tool.time.TimeUtil;
import lombok.ToString;

/**
 * user         LIUKUN
 * time         2014-4-7 14:25
 * 服务器的运行时信息
 */
@ToString
public class ServerRuntimeInfo{
    private final int serverId;
    /**
     * 启动时间,秒为单位
     */
    private final int startServerSec;

    private final Runtime rt = Runtime.getRuntime();

    public ServerRuntimeInfo( int serverId ){

        this.serverId = serverId;
        startServerSec = SystemTimer.currentTimeSecond();

    }

    public static void main( String[] args ){
        ServerRuntimeInfo info = new ServerRuntimeInfo( 10002 );
        System.out.println( info.getServerId() );
        System.out.println( info );
        System.out.println( "启动时间：" + TimeUtil.secondsToDateStr( info.getStartServerSec() ) );
        System.out.println( "开服时间：" + TimeUtil.secondsToDateStr( info.getOpenServerSec() ) );
    }


    /**
     * 获取最大可用内存，取决于-Xmx
     *
     * @return
     */
    public long getHeapMaxMemory(){
        return rt.maxMemory() >> 20;
    }

    /**
     * 获取虚拟机已经使用的操作系统内存
     *
     * @return
     */
    public long getTotalMemory(){
        return rt.totalMemory() >> 20;
    }


    public int getOpenServerSec(){
        return RunTimeDataProvider.INSTANCE.getOpenServerSec();
    }

    /**
     * 格式化启动时间
     *
     * @return 时间字符串
     */
    public int getStartServerSec(){
        return startServerSec;
    }

    public int getServerId(){
        return serverId;
    }
}
