package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 */

class PropertyDataProvider extends AbstractDataProviderWithUserName<UserProperty>{
    private static final String TABLE_NAME = "property";

    /**
     * @param uname 玩家名称
     */
    public PropertyDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected UserProperty decode( DBObject object ){
        UserProperty property = new UserProperty();
        if( object == null ) {
            return property;
        }
        property.setExp( (int) object.get( "exp" ) );
        property.setGold( (int) object.get( "gold" ) );
        property.setCash( (int) object.get( "cash" ) );
        return property;
    }

    @Override
    protected DBObject encode( UserProperty property ){

        DBObject obj = new BasicDBObject();
        obj.put( "_id", getUname() );
        obj.put( "exp", property.getExp() );
        obj.put( "gold", property.getGold() );
        obj.put( "cash", property.getCash() );
        return obj;
    }

}
