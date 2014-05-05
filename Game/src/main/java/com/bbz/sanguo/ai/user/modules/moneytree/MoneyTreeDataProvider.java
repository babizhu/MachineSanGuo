package com.bbz.sanguo.ai.user.modules.moneytree;

import com.bbz.sanguo.base.counter.Counter12;
import com.bbz.sanguo.serial.MongoSerial;
import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-29 11:41
 * 摇钱树的数据库类
 */

public class MoneyTreeDataProvider extends AbstractDataProviderWithUserName<MoneyTreeRecord>{
    private static final String TABLE_NAME = "moneyTree";

    /**
     * @param uname 玩家名称
     */
    public MoneyTreeDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected MoneyTreeRecord decode( DBObject object ){
        MoneyTreeRecord record = new MoneyTreeRecord();
        if( object != null ) {

            record.setTimes( MongoSerial.decode( object.get( "times" ) ) );
            record.setResetTimes( MongoSerial.decode( object.get( "resetTimes" ) ) );

        } else {
            record.setTimes( new Counter12() );
            record.setResetTimes( new Counter12() );
        }
        return record;
    }

    @Override
    protected DBObject encode( MoneyTreeRecord record ){
        DBObject obj = new BasicDBObject();
        obj.put( "times", MongoSerial.encode( record.getTimes() ) );
        obj.put( "resetTimes", MongoSerial.encode( record.getResetTimes() ) );
        obj.put( "_id", getUname() );
        return obj;
    }
}
