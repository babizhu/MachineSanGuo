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
    ModuleManager moduleManager = new ModuleManager( uname );

    @Test
    public void testChangeGold() throws Exception{

        PropertyModule module = new PropertyModule( moduleManager );
        for( int i = 0; i < 100; i++ ) {

            int amount = RandomUtil.getInt( 100008867 );
            module.changeAward( AwardContent.GOLD, amount, "test" );
            amount = RandomUtil.getInt( 100008867 );
            module.changeAward( AwardContent.CASH, amount, "test" );
        }


    }
}
