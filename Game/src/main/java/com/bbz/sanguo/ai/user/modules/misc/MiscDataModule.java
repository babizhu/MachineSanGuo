package com.bbz.sanguo.ai.user.modules.misc;

import com.bbz.sanguo.ai.user.ModuleManager;
import com.google.common.base.Joiner;
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

    public String getString( MiscDataKey key, Object... args ){
        String buildKey = buildKey( key, args );
        String ret = (String) data.get( buildKey );
        if( ret == null ) {
            ret = "";
        }
        return ret;
    }

    public int getInt( MiscDataKey key, Object... args ){
        String buildKey = buildKey( key, args );
        Integer ret = (Integer) data.get( buildKey );
        if( ret == null ) {
            ret = 0;
        }
        return ret;
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

    public void put( MiscDataKey key, Object value, Object... args ){
        String buildKey = buildKey( key, args );
        data.put( buildKey, value );
        //db.replace( data );不用整体更新
        db.updateWithField( buildKey, value );
    }

    /**
     * 目前是测试用的代码
     */
    void clear(){
        data.clear();
        db.remove();
    }
}
