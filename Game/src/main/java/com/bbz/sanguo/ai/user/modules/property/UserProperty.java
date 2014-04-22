package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.common.MiscUtil;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-9 20:30
 */
@Data
class UserProperty{

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 金元宝
     */
    private int gold;

    /**
     * 经验
     */
    private int exp;

    /**
     * 铜钱
     */
    private int cash;

    /**
     * 是否成年
     */
    private boolean isAdult;

    //private INonBlockingConnection  con;


    public int addGold( int changeValue ){
        this.gold += changeValue;
        return gold;
    }

    int getLevel(){
        int[] data = new int[]{1, 10, 100, 100};
        return MiscUtil.calcLevel( data, exp, 1 );
    }

    int addCash( int changeValue ){
        this.cash += changeValue;
        return cash;
    }


}