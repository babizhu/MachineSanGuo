package com.bbz.util.db;

import com.bbz.sanguo.ai.user.modules.fighters.Hero;
import com.bbz.sanguo.cfg.fighter.FighterTemplet;
import com.bbz.sanguo.cfg.fighter.FighterTempletCfg;
import com.bbz.util.identity.IdentityDataProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-14 16:19
 */

public class MongoUtilTest{
    private static final String TABLE_NAME = "MongoUtilTest";
    private static final DBCollection collection = MongoUtil.INSTANCE.getDB().getCollection( TABLE_NAME );

    @Test
    public void testAdd() throws Exception{
        collection.drop();
        long begin = System.nanoTime();
        for( int i = 0; i < 100; i++ ) {
            collection.insert( new BasicDBObject( "a" + i, "hellooooooooooooooooooooooo" + i ) );
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );

    }

    /**
     * 结论：不行mongo 无法序列化自定义类
     *
     * @throws Exception
     */
    @Test
    public void testSaveObject() throws Exception{
        collection.drop();
        FighterTemplet templet = FighterTempletCfg.getFighterTempletById( 100001 );
        Hero hero = new Hero( IdentityDataProvider.INSTANCE.get(), templet );
        hero.setExp( 2323 );
        try {

            collection.insert( new BasicDBObject( "content", hero ) );
        } catch( IllegalArgumentException e ) {
            e.printStackTrace();
        }
    }

}
