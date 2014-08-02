package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.misc.MiscDataKey;
import com.bbz.sanguo.ai.user.modules.misc.usercounter.UserCounterModule;
import com.bbz.sanguo.ai.user.modules.property.PropertyModule;
import com.bbz.sanguo.ai.user.modules.property.UserPropertyType;

/**
 * user         LIUKUN
 * time         2014/5/4 0004 0:15
 * 摇钱树，采用UserCounter版本
 */

public class MoneyTreeSpecialModule{
    public static final String FUNC_NAME = MoneyTreeSpecialModule.class + "recharge";
    public final UserCounterModule userCounterModule;
    private final PropertyModule propModule;

    public MoneyTreeSpecialModule( ModuleManager moduleManager ){
        this.userCounterModule = moduleManager.getUserCounterModule();
        this.propModule = moduleManager.getPropertyModule();
    }

    /**
     * @return 本次摇到的金币数量
     */
    public int run(){
        check();

        int money = 100;

        userCounterModule.add( MiscDataKey.MONEY_TREE, 1 );//今日摇钱次数加1
        propModule.changeValue( UserPropertyType.CASH, money, FUNC_NAME );
        return money;
    }

    private void check(){
        int maxCount = 20;
        int runCountToday = userCounterModule.get( MiscDataKey.MONEY_TREE );
        if( runCountToday > maxCount ) {
            throw new ClientException( ErrorCode.MONEY_TREE_RUN_UPPER_LIMIT );
        }
    }
}
