package com.bbz.sanguo.ai.user;

import com.bbz.sanguo.ai.user.modules.misc.usercounter.UserCounterModule;
import com.bbz.sanguo.ai.user.modules.property.PropertyModule;
import com.bbz.sanguo.ai.user.modules.recharge.RechargeModule;

/**
 * user         LIUKUN
 * time         2014-4-3 14:35
 *
 * user的模块管理器
 */

public class ModuleManager{
    public String getUserName(){
        return userName;
    }

    private final String                    userName;

    private final PropertyModule            propertyModule;

    private final UserCounterModule         userCounterModule;

    public ModuleManager( String uname ){
        this.userName = uname;
        propertyModule = new PropertyModule( this );
        userCounterModule = new UserCounterModule( this );

    }

    public RechargeModule getRechargeModule(){
        return rechargeModule;
    }

    private RechargeModule                  rechargeModule;

    public UserCounterModule getUserCounterModule(){
        return userCounterModule;
    }

    public PropertyModule getPropertyModule(){
        return propertyModule;
    }
}
