package com.bbz.sanguo.ai.user.modules.misc;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.util.D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-5-3 14:31
 */

public class MiscDataModuleTest{
    private ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
    private final MiscDataModule module = new MiscDataModule( manager );

    @Before
    public void setUp() throws Exception{

        for( int i = 0; i < 10; i++ ) {

            module.put( MiscDataKey.MOPPING_UP, i, i );
        }
        module.put( MiscDataKey.ENEMY, "大英雄" );

    }

    @Test
    public void testGetString() throws Exception{
        String str = module.getString( MiscDataKey.ENEMY );
        assertEquals( "大英雄", str );
    }

    @Test
    public void testGetInt() throws Exception{
        int count = module.getInt( MiscDataKey.MOPPING_UP, 1 );
        assertEquals( 1, count );

        count = module.getInt( MiscDataKey.MOPPING_UP, 20 );
        assertEquals( 0, count );
    }


    @After
    public void tearDown() throws Exception{
        module.clear();

    }

}
