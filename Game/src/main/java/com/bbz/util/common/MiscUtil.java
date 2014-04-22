package com.bbz.util.common;

import java.nio.ByteBuffer;

/**
 * user         LIUKUN
 * time         2014-3-28 15:13
 */

public class MiscUtil{

    /**
     * 从一个数组中，找到输入相应的位置，典型应用是通过经验获取等级
     * 举例如下<br/>
     * [25, 30, 35, 40, 45]，input=5
     * beginWith=1      return 0，即玩家拥有5经验的时候，等级为<b>0</><br/>
     * beginWith=0      return -1，即玩家拥有5经验的时候，等级为<b>-1</><br/>,这种情况一般是配置表有问题，data数组应该从0开始
     *
     * @param data      等级数组数据  例如[0,10,20,50,100]
     * @param input     输入数据      例如   35
     * @param beginWith 开始等级，通常为1或者0
     * @return 转换出来的位置
     */
    public static int calcLevel( int[] data, int input, int beginWith ){
        if( data == null ) {
            throw new IllegalArgumentException();
        }
        int level = beginWith - 1;
        for( int aData : data ) {
            if( aData > input ) {
                break;
            }
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
     * 返回按字节为单位收到的客户端传来的数据信息
     * 外部无需考虑是否flip()，内部会自行处理
     * 不会影响外部的position,limit等信息
     *
     * @param buf 数据
     * @return 易读字符串
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
