package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.common.RandomUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-11 10:55
 */

public class PropertyDataProviderTest{
    private static final String TABLE_NAME = "test";
    private static final String USER_NAME = "test_bbz";
    private static PropertyDataProvider db = new PropertyDataProvider( USER_NAME );


    @Before
    public void setUp() throws Exception{
        System.out.println("表名字" +  db.getCollection() );
        int i = RandomUtil.getInt( 100 );
        UserProperty property = new UserProperty();
        property.setGold( i * 100 );
        property.setExp( i * 111111 );
        db.replace( property );
    }


    @After
    public void tearDown() throws Exception{
        db.remove();
        db.removeAll();
    }

    @Test
    public void testFindOne() throws Exception{
        UserProperty one = db.findOne();
        System.out.println( one );
        int newExp = 123456;
        one.setExp( newExp );
        db.updateWithField( "exp", one.getExp() );
        one = db.findOne();
        assertEquals( newExp, one.getExp() );
    }
}
