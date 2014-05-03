package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.misc.MiscDataKey;
import com.bbz.util.D;
import junit.framework.TestCase;

/**
 * 当日计数器有个地方不太好测试，如何测试明天的情况呢
 * 解决方案：
 *      测试完毕之后，暂时不要删测试数据，修改系统时间后，去UserCounterModule中调用main方法，完成测试
 *      取消tearDown()中的注释后重新测试，清除数据
 *
 */
public class UserCounterModuleTest extends TestCase{
    private ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
    private UserCounterModule module = new UserCounterModule( manager );

    @Override
    public void setUp() throws Exception{
        super.setUp();
        for( int i = 0; i < 10; i++ ) {
            module.put( MiscDataKey.MOPPING_UP, i, i, i );
        }
    }

    @Override
    public void tearDown() throws Exception{
//        module.clear();
    }

    public void testGet() throws Exception{
        int count;

        count = module.get( MiscDataKey.MOPPING_UP, 1, 1 );
        assertEquals( 1, count );
    }

    public void testPut() throws Exception{
        module.put( MiscDataKey.MOPPING_UP, 100, "Today" );
        int count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 100, count );
    }

    public void testToString() throws Exception{

        System.out.println(module.toString());
    }
    public void testAdd() throws Exception{
        module.put( MiscDataKey.MOPPING_UP, 100, "Today" );
        int count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 100, count );
        int ret = module.add( MiscDataKey.MOPPING_UP, 50, "Today" );
        assertEquals( 150, ret );

        count = module.get( MiscDataKey.MOPPING_UP, "Today" );
        assertEquals( 150, count );
    }


}