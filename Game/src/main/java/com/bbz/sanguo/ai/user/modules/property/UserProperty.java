package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.util.common.MiscUtil;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-9 20:30
 */

@Data
public class UserProperty{
    /**
     * 金元宝
     */
    private int                     gold;

    /**
     * 经验
     */
    private int                     exp;

    /**
     * 等级
     */
    private int                     level;

    public void addGold( int changeValue ){
    }

    public int getLevel(){
        int[] data = new int[]{1,10,100,100};
        boolean beginWith1 = true;
        return MiscUtil.getLevel( data, exp, beginWith1 );
    }
}