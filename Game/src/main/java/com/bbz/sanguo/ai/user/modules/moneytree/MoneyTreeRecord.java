package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.base.counter.Counter12;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-28 20:07
 */
@Data
public class MoneyTreeRecord{
    /**
     * 今日摇钱次数
     */
    private Counter12 times;
    /**
     * 今日重置次数
     */
    private Counter12 resetTimes;

    public void addTimesOne(){
        times.add( 1 );
    }


    public void addResetTimesOne(){
        resetTimes.add( 1 );
    }


}
