package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.util.D;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-29 17:03
 */

public class MoneyTreeModuleTest{
    @Test
    public void testRun() throws Exception{

        ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
        MoneyTreeModule module = new MoneyTreeModule( manager );
        try {
            for( int i = 0; i < 10; i++ ) {

                module.run();
            }
        } catch( ClientException e ) {
            System.out.println( e.getCode() );
        }

    }

    /**
     * 采用了UserCounter的摇钱树版本
     *
     * @throws Exception
     */
    @Test
    public void testRunSpecial() throws Exception{


        ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
        MoneyTreeSpecialModule module = new MoneyTreeSpecialModule( manager );
        try {
            for( int i = 0; i < 10; i++ ) {

                module.run();
            }
        } catch( ClientException e ) {
            System.out.println( e.getCode() );
        }


    }
}
