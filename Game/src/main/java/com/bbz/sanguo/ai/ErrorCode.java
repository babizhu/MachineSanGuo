package com.bbz.sanguo.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * user         LIUKUN
 * time         14-3-26 下午7:50
 */
public enum ErrorCode{
    SUCCESS( 0 ),
    HERO_NOT_FOUND( 1000 ),
    EQUPMENT_NOT_FOUND( 2000 );
    private static final Map<Integer, ErrorCode> numToEnum = new HashMap<>();

    static{
        for( ErrorCode t : values() ) {

            ErrorCode s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    private final int number;
    private final String msg;

    ErrorCode( int number ){
        this.number = number;
        this.msg = "aaa";
    }

    ErrorCode( int number, String msg ){
        this.number = number;
        this.msg = msg;
    }

    public static ErrorCode fromNum( int n ){
        return numToEnum.get( n );
    }

    public int toNum(){
        return number;
    }

}
