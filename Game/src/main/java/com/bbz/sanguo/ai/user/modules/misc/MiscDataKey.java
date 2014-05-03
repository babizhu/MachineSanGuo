package com.bbz.sanguo.ai.user.modules.misc;

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
    MONEY_TREE(3);

    private final int number;

    private static final Map<Integer, MiscDataKey> numToEnum = new HashMap<>();

    static{
        for( MiscDataKey t : values() ) {

            MiscDataKey s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    MiscDataKey( int number ){
        this.number = number;
    }

    public int toNum(){
        return number;
    }

    public static MiscDataKey fromNum( int n ){
        return numToEnum.get( n );
    }

}
