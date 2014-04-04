package com.bbz.sanguo.ai.equipments;

import com.bbz.util.common.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-4 15:19
 */

public class EquipmentManagerTest{


    @Test
    public void buildData() throws Exception{
        EquipmentManager manager = new EquipmentManager( "lk" );

        manager.clear();
        System.out.println( "当前数量:" + manager.db.getCollection().count() + "条记录" );

        for( int i = 0; i < 100; i++ ) {
            int templetId = RandomUtil.getInt( 10 );
            templetId += 151001;//151001--151011
            Equipment e = new Equipment( i, templetId );
            manager.add( e );

        }


        System.out.println( "添加后的数量:" + manager.db.getCollection().count() + "条记录" );
        Equipment equipment = manager.getEquipmentById( 99 );
        int level = 30;
        manager.addLevel( equipment.getId(), level );
        equipment = manager.getEquipmentById( 99 );
        assertEquals( level, equipment.getLevel() );





    }
}
