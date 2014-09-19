package com.bbz.sanguo.db;

import com.bbz.tool.db.MongoUtil;
import com.bbz.tool.time.SystemTimer;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


/**
 * user         LIUKUN
 * time         2014-9-19 11:35
 * <p/>
 * 用于保存服务器的各种运行数据到mongo数据库中
 * 例如启动时间等
 */

public enum RunTimeDataProvider{
    INSTANCE;

    /**
     * 保存所有的服务器单例信息，
     */
    private static final String TABLE_NAME = "runtime";
    private final DBCollection collection = MongoUtil.INSTANCE.getDB().getCollection( TABLE_NAME );
    /**
     * 开服时间
     */
    int openServerSec = 0;

    /**
     * 从数据库获取开服时间，如果没有取到数据，则设置当前时间为开服时间，并存储数据库
     *
     * @return 开服时间
     */
    public int getOpenServerSec(){
        if( openServerSec == 0 ) {
            DBObject obj = collection.findOne();
            if( obj == null ) {
                openServerSec = SystemTimer.currentTimeSecond();
                collection.save( new BasicDBObject( "openServerSec", openServerSec ) );//保存到数据库
            } else {
                openServerSec = (int) obj.get( "openServerSec" );
            }
        }
        return openServerSec;
    }

}
