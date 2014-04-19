package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.util.D;
import com.bbz.util.common.RandomUtil;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-14 10:35
 */

public class PropertyModuleTest{
    private String uname = D.TEST_USER_NAME;
    private ModuleManager moduleManager = new ModuleManager( uname );
    private PropertyModule module = new PropertyModule( moduleManager );


    @Test
    public void testChangeGold() throws Exception{

        for( int i = 0; i < 2; i++ ) {
            int amount = RandomUtil.getInt( 867 );
            module.changeValue( UserPropertyType.GOLD, amount, "test" );
            amount = RandomUtil.getInt( 67 );
            module.changeValue( UserPropertyType.CASH, amount, "test" );


        }
        int cash = module.getCash();

        System.out.println( cash );
//        assertEquals( true, module.isEnough( UserPropertyType.CASH, 100 ) );
    }


}
