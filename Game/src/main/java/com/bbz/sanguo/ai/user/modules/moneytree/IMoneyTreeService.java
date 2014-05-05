package com.bbz.sanguo.ai.user.modules.moneytree;

/**
 * user         LIUKUN
 * time         2014-5-5 13:50
 * 摇钱树的接口
 */

public interface IMoneyTreeService{

    /**
     * @param type 摇钱类型0：普通 1：使用双倍
     * @return 摇到的金钱
     */
    public int run( int type );

    /**
     * 获取界面数据
     *
     * @return int[0] = 今日摇钱次数 int[1] = 今日重置次数
     */
    public int[] getData();
}
