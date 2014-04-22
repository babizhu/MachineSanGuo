package com.bbz.sanguo.base.counter;

import com.bbz.util.time.SystemTimer;
import com.bbz.util.time.TimeUtil;

/**
 * user         LIUKUN
 * time         2014-4-28 16:45
 * 晚上12点到期的计数器
 */
public class Counter12{
    private int count;
    private int timeStamp;

    public Counter12(){
    }

    public Counter12( int count, int timeStamp ){
        this.count = count;
        this.timeStamp = timeStamp;
    }


    public int getCount(){
        if( TimeUtil.isToday( timeStamp ) ) {
            return count;
        } else {
            return 0;
        }
    }

    public void set( int count ){
        this.count = count;
        timeStamp = SystemTimer.currentTimeSecond();
    }

    public int getSimeStamp(){
        return timeStamp;
    }

    public void add( int change ){
        set( getCount() + change );
    }

    @Override
    public String toString(){
        return "Counter12{" +
                "count=" + count +
                ", time=" + TimeUtil.secondsToDateStr( timeStamp ) +
                '}';
    }
}
