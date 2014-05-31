package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.util.common.CountMap;
import com.bbz.util.time.SystemTimer;
import com.bbz.util.time.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * user         LIUKUN
 * time         2014/5/2 0002 12:55
 */
@AllArgsConstructor
@Getter
@ToString
class UserCounterRecord{


    private final CountMap<String>      data;

    /**
     * 整个数据的时间戳
     * 如果此时间戳和当天时间不是同一天，则必须在进行任何操作前，清空所有的数据，如此就能保证一个时间戳管理所有数据
     */
    private  int                       timeStamp;

    /**
     * 获取数据
     * @param key       key
     * @return          数量
     */
    int get( String  key ){
        if( !isToday() ){
            data.clear();
            return 0;
        }
        return data.get( key );
    }

    /**
     *  put数据
     * @param key           key
     * @param value         要直接设置的值
     */
    void put( String key, int value ){
        if( !TimeUtil.isToday( timeStamp ) ){
            data.clear();
        }
        data.put( key, value );
        timeStamp = SystemTimer.currentTimeSecond();
    }

    /**
     * 累加数据
     * @param key           key
     * @param change        要改变的值
     * @return              添加之后的结果
     */
    int add( String key, int change ){
        if( !TimeUtil.isToday( timeStamp ) ){
            data.clear();
        }
        timeStamp = SystemTimer.currentTimeSecond();
        return data.add( key, change );
    }



    void clear(){
        data.clear();
        timeStamp = 0;
    }

    boolean isToday(){
        return TimeUtil.isToday( timeStamp );
    }
}
