package com.bbz.sanguo.ai.user.modules.fighters;

import com.bbz.sanguo.ai.user.modules.equipments.Equipment;
import com.bbz.sanguo.cfg.fighter.FighterTemplet;
import com.bbz.sanguo.cfg.fighter.FighterTempletCfg;
import com.bbz.tool.common.RandomUtil;
import com.bbz.util.D;
import com.google.common.collect.Sets;
import org.junit.Before;

import java.util.Set;

/**
 * user         LIUKUN
 * time         2014-4-14 10:07
 */

public class HeroDataProviderTest{

    private static final String USER_NAME = D.TEST_USER_NAME;
    private static final HeroDataProvider db = new HeroDataProvider( USER_NAME );

    @Before
    public void setUp() throws Exception{
        System.out.println( "表名字" + db.getCollection() );

        for( int i = 0; i < 100; i++ ) {
            int templetId = 100001 + RandomUtil.getInt( 80 );
            FighterTemplet templet = FighterTempletCfg.getFighterTempletById( templetId );
            Hero hero = new Hero( i, templet );
            hero.setPosition( i * 1000000 );
            hero.setName( "abcd" + i );
            hero.setExp( RandomUtil.getInt( 1909090 ) );

            Set<Equipment> equipments = Sets.newHashSet();
            for( int j = 0; j < 5; j++ ) {
                long equipmentId = 2;
                int equipmentTempletId = RandomUtil.getInt( 10 );
                templetId += 151001;//151001--151011
                Equipment e = new Equipment( equipmentId, equipmentTempletId );
                equipments.add( e );
            }
            hero.setEquipments( equipments );


            db.add( hero );
        }

    }
//
//    @Test
//    public void testEncode() throws Exception{
//
//    }
}
