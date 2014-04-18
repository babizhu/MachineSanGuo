package com.bbz.sanguo.ai.user.modules.bag;

import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-17 11:11
 * <p/>
 * 存放各种物品的一个容器，方便函数之间交流
 */

@Data
public class PropItem{
    /**
     * 游戏中所有的物品都拥有一个唯一的模板id
     */
    private int propId;

    private int count;

}
