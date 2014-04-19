package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.common.MiscUtil;

/**
 * user         LIUKUN
 * time         2014-4-9 20:30
 */

class UserProperty{

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 系统赠送的金元宝
     */
    //private int                     gold;


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

    String getNickName(){
        return nickName;
    }

    void setNickName( String nickName ){
        this.nickName = nickName;
    }

    int getGold(){
        return gold;
    }

    void setGold( int gold ){
        this.gold = gold;
    }

    int getExp(){
        return exp;
    }

    void setExp( int exp ){
        this.exp = exp;
    }

    int getCash(){
        return cash;
    }

    void setCash( int cash ){
        this.cash = cash;
    }

    boolean isAdult(){
        return isAdult;
    }

    void setAdult( boolean isAdult ){
        this.isAdult = isAdult;
    }
}