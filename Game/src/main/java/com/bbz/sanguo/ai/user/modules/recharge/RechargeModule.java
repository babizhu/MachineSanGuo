package com.bbz.sanguo.ai.user.modules.recharge;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.property.PropertyModule;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-4-9 17:52
 *
 * 充值管理器
 */

public class RechargeModule{

    //private final ModuleManager                 moduleManager;
    private final PropertyModule                propModule;
    private final RechargeDataProvider          db;

    private List<RechargeRecord>                rechargeRecordList;


    public RechargeModule( ModuleManager moduleManager ){
//        this.moduleManager = moduleManager;
        propModule = moduleManager.getPropertyModule();
        db = new RechargeDataProvider( moduleManager.getUserName() );
        rechargeRecordList = db.getListAll();
    }

    /**
     * 充值
     * @param money     金钱
     */
    public void recharge( int money ){
        long id = 4;
        RechargeRecord record = new RechargeRecord( id, money );
        rechargeRecordList.add( record );
        db.add( record );

        int gold = money;//应该有个兑换比例
        propModule.changeGold( gold );

    }
}
