package com.bbz.sanguo.ai.user.modules.bag;

/**
 * user         LIUKUN
 * time         2014-4-17 13:25
 */

import java.util.List;

/**
 * 游戏物品消耗接口
 */
public interface IGameObjectReduce{
    int isEnough( List<PropItem> gameObjs );

    /**
     * 消耗道具或者属性(体力，铜钱等)
     *
     * @param gameObjs
     * @return
     */
    int reduce( List<PropItem> gameObjs );


}
