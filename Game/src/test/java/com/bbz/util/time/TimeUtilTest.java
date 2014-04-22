package com.bbz.util.time;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-28 17:46
 */

public class TimeUtilTest{
    @Test
    public void testIsToday() throws Exception{
        int second = 1397013038;
        System.out.print( "当前日期" + TimeUtil.secondsToDateStr( SystemTimer.currentTimeSecond() ) );
        System.out.println( " 与" + TimeUtil.secondsToDateStr( second ) + "不是同一天" );
        assertEquals( false, TimeUtil.isToday( second ) );
    }

    @Test
    public void testCreateBySecond() throws Exception{
        int second = 0;
        DateTime dt = TimeUtil.createBySecond( second );
        System.out.println( TimeUtil.secondsToDateStr( (int) (dt.getMillis() / 1000) ) );

    }


    @Test
    public void testSecondsToDateStr() throws Exception{
        int second = 1397013038;
        String str = TimeUtil.secondsToDateStr( second );
        System.out.println( str );
        assertEquals( "2014-04-09 11:10:38", str );
    }

    @Test
    public void getRemainSecond() throws Exception{
        int second = 1397013038;//"2014-04-09 11:10:38"
        DateTime time = TimeUtil.createBySecond( second );
        int remainSecond = TimeUtil.getRemainSecond( time );
        assertEquals( 0, remainSecond );
        time = new DateTime();
        time = time.withFieldAdded( DurationFieldType.minutes(), 3 );//当前时间增加3分钟,180秒
        remainSecond = TimeUtil.getRemainSecond( time );
        assertEquals( 180, remainSecond );
    }
}
