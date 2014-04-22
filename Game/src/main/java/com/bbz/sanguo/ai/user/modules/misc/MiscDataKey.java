package com.bbz.sanguo.ai.user.modules.misc;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-5-3 11:41
 */
public enum MiscDataKey{
    ENEMY( 1 ), /**
     * 个关卡的扫荡次数
     */
    MOPPING_UP( 2 ),

    /**
     * 摇钱树
     */
    MONEY_TREE( 3 );
    private static final Map<Integer, MiscDataKey> numToEnum = new HashMap<>();

    static{
        for( MiscDataKey t : values() ) {

            MiscDataKey s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    private final int number;

    MiscDataKey( int number ){
        this.number = number;
    }

    public static MiscDataKey fromNum( int n ){
        return numToEnum.get( n );
    }

    public String buildKey( Object[] args ){
        String ret = number + "_"; //_为分隔符，防止1和11分不清楚
        // (例如两个key为a和a1，a带个参数1，a1不带参数，则生成的key相同)
        Joiner.on( "_" ).join( args );
        return ret;
    }

    public int toNum(){
        return number;
    }

}
