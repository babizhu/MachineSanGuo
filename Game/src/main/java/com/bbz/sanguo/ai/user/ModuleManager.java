package com.bbz.sanguo.ai.user;

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

    public PropertyModule getPropertyModule(){
        return propertyModule;
    }

    private final PropertyModule            propertyModule;
    private RechargeModule                  rechargeModule;

    public ModuleManager( String uname ){
        this.userName = uname;
        propertyModule = new PropertyModule( this );

    }

    public RechargeModule getRechargeModule(){
        return rechargeModule;
    }
}
