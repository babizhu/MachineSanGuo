package com.bbz.util.common;

import com.bbz.util.D;
import org.joda.time.DateTime;

import java.nio.ByteBuffer;

/**
 * user         LIUKUN
 * time         2014-3-28 15:13
 */

public class MiscUtil{

    /**
     * 从一个数组中，找到输入相应的位置，典型应用是通过经验获取等级,允许输入数据超过数组的最大值<br/>
     * 举例如下<br/>
     * [0,10,20,50,100]，input=35
     * beginWith1=true      return 3，即玩家拥有35经验的时候，等级为<b>3</><br/>
     * beginWith1=false     return 2，即玩家拥有35经验的时候，等级为<b>2</><br/>
     *
     * @param data       等级数组数据  例如[0,10,20,50,100]
     * @param input      输入数据      例如   35
     * @param beginWith1 等级从0还是从1开始计算
     * @return 输入数据在数组中的位置
     */
    public static int getLevel( int[] data, int input, boolean beginWith1 ){
        if( data == null ) {
            throw new IllegalArgumentException();
        }
        int level = 0;
        if( input > data[data.length - 1] ) {
            level = data.length;
        } else {
            for( int i = 0; i < data.length; i++ ) {
                if( data[i] > input ) {
                    level = i - 1;
                    break;
                }
            }
        }

        if( beginWith1 ) {
            level++;
        }

        return level;
    }

    public static boolean isWindowsOS(){
        boolean isWindowsOS = false;
        String osName = System.getProperty( "os.name" );
        if( osName.toLowerCase().contains( "windows" ) ) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 把一个用秒数保存的时间值转换为易读的字符串
     *
     * @param seconds   秒数
     * @return 易读的字符串
     */
    public static String secondsToDateStr( int seconds ){
        DateTime dateTime = new DateTime( seconds * 1000l );
        return dateTime.toString( D.DATA_FORMAT_STR );
    }

    /**
     * 返回按字节为单位收到的客户端传来的数据信息
     * 外部无需考虑是否flip()，内部会自行处理
     * 不会影响外部的position,limit等信息
     *
     * @param buf   数据
     * @return      易读字符串
     *
     */
    public static String bufToString( ByteBuffer buf ){
        ByteBuffer copy = buf.asReadOnlyBuffer();
        if( copy.position() != 0 ) {
            copy.flip();
        }
        StringBuilder sb = new StringBuilder( "[" );
        while( copy.hasRemaining() ) {
            int b = copy.get() & 0xff;
            sb.append( b ).append( " " );//不转换为int有可能出现负数
        }
        sb.append( "]" );
        return sb.toString();
    }
}
