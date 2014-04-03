package com.bbz.sanguo.ai.fighters;

import com.bbz.sanguo.ai.equipments.Equipment;
import com.bbz.util.common.MiscUtil;
import com.bbz.util.db.IdentityObj;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:36
 * 玩家手中的英雄
 */


@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
@Data()
public class Hero extends BaseFighter implements IdentityObj{

    /**
     * 唯一标识
     */
    private final long          id;

    /**
     * 名字
     */
    private String              name;
    /**
     * 装备
     */
    private Set<Equipment>      equipments = Sets.newHashSet();

    /**
     * 所在位置
     * 0表示不在战斗位置上，处于闲置状态
     */
    private int                 position;

    /**
     * 经验
     */
    private int                 exp;

    /**
     * 升级
     */
    public void levelUp(){

    }

    /**
     * 通过经验计算等级,等级从1开始，即0经验等于1级
     *
     * @return
     */
    public int getLevel(){
        int[] data = new int[]{0, 100, 500, 200, 10000, 50000};//暂时手写，应该从配置表中获取
        return MiscUtil.getLevel( data, exp, true );
    }

    public static void main( String[] args ){
        Hero h = new Hero( 34 );
        System.out.println(h );
    }
}
