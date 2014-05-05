package com.bbz.sanguo.ai.user.modules.recharge;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         14-3-26 下午2:38
 */

public class RechargeDataProvider extends AbstractDataProviderWithIdentity<RechargeRecord>{

    private static final String TABLE_NAME = "recharge";

    public RechargeDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected RechargeRecord decode( DBObject object ){
        RechargeRecord record = new RechargeRecord( (Long) object.get( "_id" ) );
        record.setMoney( (int) object.get( "money" ) );
        record.setTime( (Integer) object.get( "time" ) );
        return record;
    }


    @Override
    protected DBObject encode( RechargeRecord record ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", record.getId() );
        obj.put( "uname", getUname() );
        obj.put( "money", record.getMoney() );
        obj.put( "time", record.getTime() );
        return obj;
    }

}