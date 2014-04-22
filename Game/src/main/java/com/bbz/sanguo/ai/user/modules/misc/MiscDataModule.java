package com.bbz.sanguo.ai.user.modules.misc;

import com.bbz.sanguo.ai.user.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-5-3 11:39
 * 一个方便的存放杂项数据的地方，实用价值如何，需要在实际使用中确定
 */

public class MiscDataModule{
    private static Logger logger = LoggerFactory.getLogger( MiscDataModule.class );

    private final Map<String, Object> data;
    private final MiscDataProvider db;

    public MiscDataModule( ModuleManager manager ){
        db = new MiscDataProvider( manager.getUserName() );
        data = db.findOne();
    }

    public String getString( DataKey key, Object... args ){
        String keyStr = buildKey( key, args );
        String ret = (String) data.get( keyStr );
        if( ret == null ) {
            ret = "";
        }
        return ret;
    }

    public int getInt( DataKey key, Object... args ){
        String keyStr = buildKey( key, args );
        Integer ret = (Integer) data.get( keyStr );
        if( ret == null ) {
            ret = 0;
        }
        return ret;
    }

    private String buildKey( DataKey key, Object[] args ){
        String ret = key.toString();
        for( Object arg : args ) {
            ret += arg;
        }
        return ret;
    }

    public void put( DataKey key, Object value, Object... args ){
        String keyStr = buildKey( key, args );
        data.put( keyStr, value );
        //db.replace( data );不用整体更新
        db.updateWithField( keyStr, value );
    }

    /**
     * 目前是测试用的代码
     */
    void clear(){
        data.clear();
        db.remove();
    }
}
