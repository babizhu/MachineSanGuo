package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 */

public class PropertyFataProvider extends AbstractDataProviderWithUserName<UserProperty>{
    private static final String TABLE_NAME = "property";

    /**
     * @param uname     玩家名称
     */
    public PropertyFataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected UserProperty decode( DBObject object ){
        UserProperty property = new UserProperty();
        property.setExp( (int) object.get( "exp" ) );
        property.setGold( (int) object.get("gold") );
        return property;
    }

    @Override
    protected DBObject encode( UserProperty property ){

        DBObject obj = new BasicDBObject();
        obj.put( "_id", getUname() );
        obj.put( "exp", property.getExp() );
        obj.put( "gold", property.getGold() );
        return obj;
    }

}
