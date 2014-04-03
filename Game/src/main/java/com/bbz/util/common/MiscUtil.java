package com.bbz.util.common;

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
     * @param data          数组数据
     * @param input         输入数据
     * @param beginWith1    等级从0还是从1开始计算
     * @return
     *          输入数据在数组中的位置
     */
    public static int getLevel( int[] data, int input, boolean beginWith1 ){
        if( data == null ) {
            throw new IllegalArgumentException(  );
        }
        int level = 0;
        if( input > data[data.length-1] ){
            level = data.length;
        }else {
            for( int i = 0; i < data.length; i++ ) {
                if( data[i] > input ) {
                    level = i - 1;
                    break;
                }
            }
        }

        if( beginWith1 ){
            level++;
        }

        return level;
    }
}
