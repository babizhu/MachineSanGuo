package com.bbz.sanguo.ai.user.modules.bags;
import com.bbz.sanguo.ai.ErrorCode;

/**
 * user         LIUKUN
 * time         2014-4-9 10:51
 */

public interface IBag{

    public ErrorCode add( PropUnit unit );
    public ErrorCode remove( PropUnit unit );

    /**
     * 计算放入当前物品之后，额外所需要的格子数，不包括原来所占有的格子
     * @param unit		要放入的物品
     * @return          额外所需的格子
     */
    public int calcNeedGridCount( PropUnit unit );

    /**
     * 当前占用了多少个格子
     * @return
     */
    public int getGridCount();

    /**
     * 检查所需的道具数量是否足够
     * @param unit
     * @return
     */
    public ErrorCode checkPropIsEnough( PropUnit unit );
}
