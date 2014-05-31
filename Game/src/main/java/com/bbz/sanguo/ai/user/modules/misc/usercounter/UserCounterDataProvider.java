package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.util.common.CountMap;
import com.bbz.util.db.AbstractDataProviderWithUserName;
import com.bbz.util.time.SystemTimer;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014/5/2 0002 13:06
 *
 */

class UserCounterDataProvider extends AbstractDataProviderWithUserName<UserCounterRecord>{

    private static final String TABLE_NAME = "userCounter";
    public static final String TIME_STAMP_FIELD = "timeStamp";


    @Override
    public UserCounterRecord findOne(){
        DBObject condition = new BasicDBObject( "_id", uname );
        return decode( collection.findOne( condition ) );
    }
    /**
     * @param uname 玩家名称
     */
    public UserCounterDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    /**
     * 更新某个字段的值，更新数据字段的时候要同时更新时间戳
     * @param key               key
     * @param value             value
     */
    public void update( String key, int value ){
//        System.out.println(new Exception().getStackTrace()[1].getMethodName());
        DBObject obj = new BasicDBObject( key, value );
        obj.put( TIME_STAMP_FIELD, SystemTimer.currentTimeSecond() );
        updateWithField( obj );
    }

    @Override
    protected UserCounterRecord decode( DBObject obj ){
        CountMap<String> data = new CountMap<>();
        int timeStamp = 0;

        if( obj != null ) {
            obj.removeField( "_id" );
            timeStamp = (int) obj.removeField( TIME_STAMP_FIELD );

            for( String key : obj.keySet() ) {
                data.put( key, (Integer) obj.get( key ) );
            }
        }
        return new UserCounterRecord( data, timeStamp );
    }

    @Override
    protected DBObject encode( UserCounterRecord record ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", getUname() );
        obj.put( TIME_STAMP_FIELD, record.getTimeStamp() );

        for( Map.Entry<String, Integer> entry : record.getData().entrySet() ) {
            obj.put( entry.getKey(), entry.getValue() );
        }
        return obj;
    }
}