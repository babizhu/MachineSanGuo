package com.bbz.sanguo.ai.user.modules.fighters;

import com.bbz.sanguo.ai.user.modules.equipments.Equipment;
import com.bbz.sanguo.ai.user.modules.equipments.EquipmentModule;
import com.bbz.sanguo.cfg.fighter.FighterTemplet;
import com.bbz.sanguo.cfg.fighter.FighterTempletCfg;
import com.bbz.tool.common.Transform;
import com.bbz.tool.db.AbstractDataProviderWithIdentity;
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

    public static void main( String[] args ){
        System.out.println();
    }

    @Override
    protected Hero decode( DBObject object ){
        int templetId = (int) object.get( "templetId" );
        FighterTemplet templet = FighterTempletCfg.getFighterTempletById( templetId );
        Hero hero = new Hero( (Long) object.get( "_id" ), templet );
        hero.setName( (String) object.get( "name" ) );
        Set<Equipment> equipments = Sets.newHashSet();
        long[] arr = Transform.ArrayType.toLongs( (String) object.get( "equipmentS" ) );
        EquipmentModule em = new EquipmentModule( "lk" );//TODO 需要考虑实际如何操作
        for( long id : arr ) {
            Equipment e = em.getEquipmentById( id );
            equipments.add( e );
        }

        hero.setEquipments( equipments );
        hero.setPosition( (int) object.get( "position" ) );


        return hero;
    }

    @Override
    protected DBObject encode( Hero hero ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", hero.getId() );
        //obj.put( "uname", getUname() );
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