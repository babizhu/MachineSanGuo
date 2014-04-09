package com.bbz.sanguo.ai.user.modules.fighters;

import com.bbz.sanguo.ai.user.modules.equipments.Equipment;
import com.bbz.sanguo.ai.user.modules.equipments.EquipmentManager;
import com.bbz.sanguo.cfg.fighter.FighterTemplet;
import com.bbz.sanguo.cfg.fighter.FighterTempletCfg;
import com.bbz.util.common.Transform;
import com.bbz.util.db.AbstractDataProviderWithIdentity;
import com.google.common.collect.Sets;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Set;

/**
 * user         LIUKUN
 * time         14-3-26 下午2:38
 */

public class HeroDataProvider extends AbstractDataProviderWithIdentity<Hero>{

    private static final String TABLE_NAME = "hero";

    public HeroDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected Hero decode( DBObject object ){
        Hero hero = new Hero( (Long) object.get( "_id" ) );
        hero.setName( (String) object.get( "name" ) );
        Set<Equipment> equipments = Sets.newHashSet();
        long[] arr = Transform.ArrayType.toLong( (String) object.get( "equipmentS" ) );
        EquipmentManager em = new EquipmentManager( "lk" );//TODO 需要考虑实际如何操作
        for( long id : arr ) {
            Equipment e = em.getEquipmentById( id );
            equipments.add( e );
        }

        hero.setEquipments( equipments );
        hero.setPosition( (int) object.get( "position" ) );
        int templetId = (int) object.get( "templetId" );
        FighterTemplet templet = FighterTempletCfg.getFighterTempletById( templetId );
        hero.setTemplet( templet );
        return hero;
    }


    @Override
    protected DBObject encode( Hero hero ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", hero.getId() );
        obj.put( "uname", getUname() );
        obj.put( "name", hero.getName() );
        obj.put( "position", hero.getPosition() );
        String equipmentStr = "";
        for( Equipment e : hero.getEquipments() ) {
            equipmentStr += e.getId();
            equipmentStr += ",";
        }
        obj.put( "equipmentS", equipmentStr );
        obj.put( "templetId", hero.getTemplet().getId() );
        return obj;
    }

}