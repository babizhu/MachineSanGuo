package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.sanguo.ai.user.ModuleManager;

/**
 * user         LIUKUN
 * time         2014-4-8 16:28
 * 管理玩家的属性包括
 * 铜钱
 * 元宝
 * 体力
 *
 *
 */

public class PropertyModule{

    private final PropertyFataProvider          db;

    private final UserProperty                  property;


    public PropertyModule( ModuleManager moduleManager ){
        db = new PropertyFataProvider( moduleManager.getUserName() );
        property = db.get();

        //this.moduleManager = moduleManager;
    }


    /**
     * 修改金子的数目
     * @param changeValue
     */
    public void changeGold( int changeValue ){
        gold += changeValue;
    }
}
