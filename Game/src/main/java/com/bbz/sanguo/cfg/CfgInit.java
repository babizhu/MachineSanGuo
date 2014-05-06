package com.bbz.sanguo.cfg;

import com.bbz.sanguo.cfg.corona.CoronaBankTempletCfg;
import com.bbz.sanguo.cfg.corona.CoronaTypeTempletCfg;
import com.bbz.sanguo.cfg.equipment.EquipmentTempletCfg;
import com.bbz.sanguo.cfg.fighter.FighterTempletCfg;


/**
 * user         LIUKUN
 * time         2014-4-25 13:52
 * 初始化所有的配置文件
 */

public class CfgInit{
    public static void init(){

        CoronaBankTempletCfg.init();
        CoronaTypeTempletCfg.init();
        EquipmentTempletCfg.init();
        FighterTempletCfg.init();

    }
}