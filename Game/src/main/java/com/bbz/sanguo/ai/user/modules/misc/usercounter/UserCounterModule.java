package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.misc.MiscDataKey;

/**
 * user         LIUKUN
 * time         2014/5/1 0001 11:45<br/>
 * <p/>
 * 午夜12点，所有的数据项都会清0<br/>
 * 数据库中的过期数据在构造函数中清除<br/>
 * 所以如果程序连续运行两天的话，就会出现数据库数据和内存数据不统一的情况，因为没有机会调用构造函数<br/>
 * 只要保证内存数据逻辑正确即可<br/>
 */
public class UserCounterModule{
//    private static Logger                   logger = LoggerFactory.getLogger( UserCounterModule.class );

    private final UserCounterRecord data;

    private final UserCounterDataProvider db;

    public UserCounterModule( ModuleManager manager ){
        db = new UserCounterDataProvider( manager.getUserName() );
        data = db.findOne();
        if( !data.isToday() ) {
            clear();
        }
    }

    public int get( MiscDataKey key, Object... args ){
        String buildKey = key.buildKey( args );

        return data.get( buildKey );
    }

    public void put( MiscDataKey key, int value, Object... args ){
        String buildKey = key.buildKey( args );
        data.put( buildKey, value );
        db.update( buildKey, value );

    }

    public int add( MiscDataKey key, int change, Object... args ){
        String buildKey = key.buildKey( args );
        int count = data.add( buildKey, change );
        db.update( buildKey, count );
        return count;
    }

    public void clear(){
        db.remove();
        data.clear();
    }

    @Override
    public String toString(){
        return "UserCounterModule{" +
                "data=" + data +
                '}';
    }

}
