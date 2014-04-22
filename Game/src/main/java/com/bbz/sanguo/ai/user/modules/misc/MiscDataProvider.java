package com.bbz.sanguo.ai.user.modules.misc;

import com.bbz.util.db.AbstractDataProviderWithUserName;
import com.google.common.collect.Maps;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 */

class MiscDataProvider extends AbstractDataProviderWithUserName<Map<String, Object>>{
    private static final String TABLE_NAME = "miscData";

    /**
     * @param uname 玩家名称
     */
    public MiscDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected Map<String, Object> decode( DBObject obj ){
        Map<String, Object> data = Maps.newHashMap();
        if( obj != null ) {
            for( String key : obj.keySet() ) {
                data.put( key, obj.get( key ) );
            }
        }
        return data;
    }

    @Override
    protected DBObject encode( Map<String, Object> stringObjectMap ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", getUname() );
        for( Map.Entry<String, Object> entry : stringObjectMap.entrySet() ) {
            obj.put( entry.getKey(), entry.getValue() );
        }
        return obj;
    }


//    @Override
//    protected UserProperty decode( DBObject object ){
//        UserProperty property = new UserProperty();
//        if( object == null ) {
//            return property;
//        }
//        property.setExp( (int) object.get( "exp" ) );
//        property.setGold( (int) object.get( "gold" ) );
//        property.setCash( (int) object.get( "cash" ) );
//        property.setExp( (int) object.get( "exp" ) );
//        property.setAdult( (Boolean) object.get( "isAdult" ) );
//        property.setNickName( (String) object.get( "nickName" ) );
//        return property;
//    }
//
//    @Override
//    protected DBObject encode( Object o ){
//        return null;
//    }
//
//    @Override
//    protected DBObject encode( UserProperty property ){
//
//        DBObject obj = new BasicDBObject();
//        obj.put( "_id", getUname() );
//        obj.put( "exp", property.getExp() );
//        obj.put( "gold", property.getGold() );
//        obj.put( "cash", property.getCash() );
//        obj.put( "isAdult", property.isAdult() );
//        obj.put( "nickName", property.getNickName() );
//        return obj;
//    }

}
