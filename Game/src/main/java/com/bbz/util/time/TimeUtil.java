package com.bbz.util.time;

import com.bbz.util.D;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * user         LIUKUN
 * time         2014-4-28 17:30
 */

public class TimeUtil{
    /**
     * 判断输入的时间点是否今天
     *
     * @param time 用秒为单位表述的一个时间点
     * @return true     是今天
     */
    public static boolean isToday( int time ){
        LocalDate localDate = new LocalDate();
        LocalDate compare = new LocalDate( time * 1000L );
        return localDate.equals( compare );
    }

    /**
     * 通过秒创建出一个 DateTime 对象
     *
     * @param second 传入的秒数
     * @return 时间对象
     */
    public static DateTime createBySecond( int second ){
        return new DateTime( second * 1000L );
    }

    /**
     * 把一个用秒数保存的时间值转换为易读的字符串
     *
     * @param seconds 秒数
     * @return 易读的字符串
     */
    public static String secondsToDateStr( int seconds ){
        DateTime dateTime = new DateTime( seconds * 1000l );
        return dateTime.toString( D.DATA_FORMAT_STR );
    }

    /**
     * 返回当前时间-输入时间的值，如果为负数则返回0
     *
     * @param dt 要比较的时间
     * @return 剩余的时间——秒数，小于0则返回0
     */
    public static int getRemainSecond( DateTime dt ){
        int ret = (int) (dt.getMillis() / 1000 - SystemTimer.currentTimeSecond());
        return Math.max( 0, ret );
    }

}
