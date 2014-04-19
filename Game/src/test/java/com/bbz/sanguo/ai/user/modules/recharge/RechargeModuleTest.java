package com.bbz.sanguo.ai.user.modules.recharge;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.property.PropertyModule;
import com.bbz.util.D;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-17 16:57
 */

public class RechargeModuleTest{
    private String uname = D.TEST_USER_NAME;
    private ModuleManager moduleManager = new ModuleManager( uname );
    private RechargeModule module = new RechargeModule( moduleManager );
    private PropertyModule propertyModule = moduleManager.getPropertyModule();

    @Test
    public void testRecharge() throws Exception{

        System.out.println( "gold: " + propertyModule.getGold() + " money: " + module.getMoney() );
        module.recharge( 100 );
        System.out.println( "gold: " + propertyModule.getGold() + " money: " + module.getMoney() );

    }

    @Test
    public void testGetMoney() throws Exception{

    }
}
