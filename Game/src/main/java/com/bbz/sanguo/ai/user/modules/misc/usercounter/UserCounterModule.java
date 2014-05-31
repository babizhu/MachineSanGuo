package com.bbz.sanguo.ai.user.modules.misc.usercounter;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.bbz.sanguo.ai.user.modules.misc.MiscDataKey;
import com.bbz.util.D;
import com.google.common.base.Joiner;

/**
 * user         LIUKUN
 * time         2014/5/1 0001 11:45
 * 午夜12点到期的计数器
 * 数据库中的过期数据在构造函数中清除
 * 那么如果程序连续运行两天的话，就会出现数据库数据和内存数据不统一的情况
 * 只要保证内存数据逻辑正确即可
 */
public class UserCounterModule{
//    private static Logger                   logger = LoggerFactory.getLogger( UserCounterModule.class );

    private final UserCounterRecord                 data;

    private final UserCounterDataProvider           db;

    public UserCounterModule( ModuleManager manager ){
        db = new UserCounterDataProvider( manager.getUserName() );
        data = db.findOne();
        if( !data.isToday() ){
            clear();
        }

    }

    public int get( MiscDataKey key, Object... args ){
        String buildKey = buildKey( key, args );
        int count = data.get( buildKey );

        return count;
    }

    public void put( MiscDataKey key, int value, Object... args ){
        String buildKey = buildKey( key, args );
        data.put( buildKey, value );
        db.update( buildKey, value );

    }

    public int add( MiscDataKey key, int change, Object... args ){
        String buildKey = buildKey( key, args );
        int count = data.add( buildKey, change );
        db.update( buildKey, count );
        return count;
    }

    public void clear(){
        db.remove();
        data.clear();
    }

    /**
     * 为了性能考虑把key转为了数字，减少存储所需要的话费，劣势在于调试不变
     * 可根据需要灵活变更
     * @param key       key
     * @param args      参数
     * @return          完整的参数
     */
    private String buildKey( MiscDataKey key, Object[] args ){
        String ret = key.toNum() + "_";//_为分隔符，防止1和11分不清楚
        ret += Joiner.on( "_" ).join(args);

        return ret;
    }

    @Override
    public String toString(){
        return "UserCounterModule{" +
                "data=" + data +
                '}';
    }

    public static void main( String[] args ){
        ModuleManager manager = new ModuleManager( D.TEST_USER_NAME );
        UserCounterModule module = new UserCounterModule( manager );

        System.out.println( module );
        int count = module.get( MiscDataKey.MOPPING_UP, 1, 1 );
        System.out.println( count );//expect 0
//        module.put( MiscDataKey.MOPPING_UP, 1999,1,2 );
        System.out.println( module.get( MiscDataKey.MOPPING_UP,1,2 ));//expect 1999




    }
}
