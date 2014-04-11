package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

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

    private final PropertyDataProvider          db;
    private final UserProperty                  property;


    public PropertyModule( ModuleManager moduleManager ){
        db = new PropertyDataProvider( moduleManager.getUserName() );
        DBObject condition = new BasicDBObject(  );
        property = db.findOne( condition );

        //this.moduleManager = moduleManager;
    }


    /**
     * 修改金子的数目
     * @param changeValue
     */
    public void changeGold( int changeValue ){

        property.addGold( changeValue );
    }
}
