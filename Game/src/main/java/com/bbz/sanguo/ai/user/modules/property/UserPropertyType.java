package com.bbz.sanguo.ai.user.modules.property;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-4-17 15:49
 */

public enum UserPropertyType{
    CASH( 100001 ),
    GOLD( 100002 ),;
    private static final Map<Integer, UserPropertyType> numToEnum = Maps.newHashMap();

    static{
        for( UserPropertyType t : values() ) {

            UserPropertyType s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    private final int number;

    UserPropertyType( int number ){
        this.number = number;
    }

    public static UserPropertyType fromNum( int n ){
        return numToEnum.get( n );
    }

    public int toNum(){
        return number;
    }


}
