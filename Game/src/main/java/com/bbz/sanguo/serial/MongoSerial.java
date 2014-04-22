package com.bbz.sanguo.serial;

import com.bbz.sanguo.base.counter.Counter12;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-29 14:21
 * 一个专门的序列化类，用来把一些通用的普通类序列为方便mongo使用的DBObject
 */

public class MongoSerial{

    public static final DBObject encode( Counter12 counter ){
        DBObject object = new BasicDBObject( "count", counter.getCount() );
        object.put( "time", counter.getTime() );
        return object;
    }

    public static final Counter12 decode( Object object ){
        DBObject obj = (DBObject) object;
        Counter12 counter = new Counter12( (int) obj.get( "count" ), (int) obj.get( "time" ) );
        return counter;
    }
}
