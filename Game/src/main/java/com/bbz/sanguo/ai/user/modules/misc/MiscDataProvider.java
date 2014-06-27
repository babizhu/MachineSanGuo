package com.bbz.sanguo.ai.user.modules.misc;

import com.bbz.tool.db.AbstractDataProviderWithUserName;
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
    protected DBObject encode( Map<String, Object> map ){
        DBObject obj = new BasicDBObject();

        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            obj.put( entry.getKey(), entry.getValue() );
        }
        return obj;
    }


}
