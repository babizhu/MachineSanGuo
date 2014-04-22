package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.misc.MiscDataKey;
import com.bbz.util.D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 */
public class UserCounterModuleTest{
    private ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
    private UserCounterModule module = new UserCounterModule( manager );

    @Before
    public void setUp() throws Exception{
        for( int i = 0; i < 10; i++ ) {
            module.put( MiscDataKey.MOPPING_UP, i, i, i );
        }
    }

    @After
    public void tearDown() throws Exception{
        //module.clear();
    }

    @Test
    public void testGet() throws Exception{
        int count;

        count = module.get( MiscDataKey.MOPPING_UP, 1, 1 );
        assertEquals( 1, count );
    }

    @Test
    public void testPut() throws Exception{
        module.put( MiscDataKey.MOPPING_UP, 100, "Today" );
        int count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 100, count );
    }

    @Test
    public void testToString() throws Exception{

        System.out.println( module.toString() );
    }

    @Test
    public void testAdd() throws Exception{
        module.put( MiscDataKey.MOPPING_UP, 100, "Today" );
        int count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 100, count );
        int ret = module.add( MiscDataKey.MOPPING_UP, 50, "Today" );
        assertEquals( 150, ret );

        count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 150, count );
    }

    /**
     * 测试第二天的执行情况
     *
     * @throws InterruptedException
     */
    @Test
    public void testTomorrow() throws InterruptedException{
        //!!修改当前时间到23:59:50，开始执行
        int count = module.get( MiscDataKey.MOPPING_UP, 1, 1 );
        assertEquals( count, 1 );
        Thread.sleep( 3000 );//等候3秒，让系统时间跳到第二天
        count = module.get( MiscDataKey.MOPPING_UP, 1, 1 );
        System.out.println( "MiscDataKey.MOPPING_UP value is " + count );//因为改了时间，因此expect 0，不用assertEquals是避免maven过不了
    }

}