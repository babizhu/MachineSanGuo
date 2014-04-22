package com.bbz.util.time;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * user         LIUKUN
 * time         2014-4-7 14:32
 * 时间线程，在对时间精度要求不高的系统中，替代多次反复的System.currentTimeMillis()调用
 */

/**
 * 线程工厂，设置线程为守护线程，随着main函数的结束而退出
 */
class DaemonThreadFactory implements ThreadFactory{
    public Thread newThread( Runnable r ){
        Thread t = new Thread( r );
        t.setDaemon( true );
        return t;
    }
}

public class SystemTimer{
    private static final ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor( new DaemonThreadFactory() );
    /**
     * 执行的时间间隔，也就是说没50ms取一次系统时间
     */
    private final static int TICK_UNIT = 50;
    private volatile static long time = System.currentTimeMillis();

    private SystemTimer(){

    }

    public static long currentTimeMillis(){
        return time;
    }

    public static int currentTimeSecond(){
        return (int) (time / 1000);
    }

    public static void main( String[] args ) throws InterruptedException{
        for( int i = 0; i < 10; i++ ) {
            System.out.println( currentTimeMillis() );
            Thread.sleep( 1000 );
        }
    }

    private static class TimerTicker implements Runnable{

        @Override
        public void run(){
            time = System.currentTimeMillis();
        }

    }

    static{
        TimerTicker timerTicker = new TimerTicker();
        s.scheduleAtFixedRate( timerTicker, TICK_UNIT, TICK_UNIT,
                TimeUnit.MILLISECONDS );
        Runtime.getRuntime().addShutdownHook( new Thread(){
            @Override
            public void run(){
                System.out.println( "SystemTimer thread will down" );
                s.shutdown();

            }
        } );
    }
}
