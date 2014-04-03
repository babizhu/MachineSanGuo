package com.bbz.util.common;

import java.util.Random;

/**
 * Created by Administrator on 14-1-20.
 */
public class RandomUtil{

    private static Random r = new Random();

    /**
     * 随机获取一个[0,max)范围的整型数字
     *
     * @param max 随机值上限
     * @return 随机返回的整型数字
     */
    public static int getInt(int max){
        return r.nextInt(max);
    }
}
