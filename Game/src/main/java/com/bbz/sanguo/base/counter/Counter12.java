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
    private int time;

    public Counter12(){
    }

    public Counter12( int count, int time ){
        this.count = count;
        this.time = time;
    }


    public int getCount(){
        if( TimeUtil.isToday( time ) ) {
            return count;
        } else {
            return 0;
        }
    }

    public void setCount( int count ){
        this.count = count;
        time = SystemTimer.currentTimeSecond();
    }

    public int getTime(){
        return time;
    }

    public void addCount( int change ){
        setCount( getCount() + change );
    }

    @Override
    public String toString(){
        return "Counter12{" +
                "count=" + count +
                ", time=" + TimeUtil.secondsToDateStr( time ) +
                '}';
    }
}
