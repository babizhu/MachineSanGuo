package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.property.PropertyModule;

/**
 * user         LIUKUN
 * time         2014-4-28 19:56
 * 摇钱树模块,内部记录当日摇钱次数以及重置次数版本，未采用UserCount
 */

public class MoneyTreeModule{

    private final MoneyTreeDataProvider db;
    private final PropertyModule propModule;
    private MoneyTreeRecord record;


    public MoneyTreeModule( ModuleManager moduleManager ){
        db = new MoneyTreeDataProvider( moduleManager.getUserName() );
        propModule = moduleManager.getPropertyModule();
        record = db.findOne();
//        System.out.println( record );
    }

    /**
     * @return 本次摇到的金币数量
     */
    public int run(){
        check();

        int money = 100;
        record.addTimesOne();

        db.replace( record );
        return money;
    }

    private void check(){
        int maxCount = 43;
        if( record.getTimes().getCount() > maxCount ) {
            throw new ClientException( ErrorCode.MONEY_TREE_RUN_UPPER_LIMIT );
        }
    }


}
