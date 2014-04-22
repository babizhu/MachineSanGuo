package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.util.D;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-29 17:03
 */

public class MoneyTreeModuleTest{
    @Test
    public void testRun() throws Exception{
        int second = 1398765186;
        System.out.println( new DateTime( second * 1000l ) );
        ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
        MoneyTreeModule module = new MoneyTreeModule( manager );
        for( int i = 0; i < 10; i++ ) {

            module.run();
        }

    }
}
