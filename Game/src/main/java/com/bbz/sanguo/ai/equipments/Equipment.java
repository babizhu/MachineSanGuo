package com.bbz.sanguo.ai.equipments;

import com.bbz.sanguo.cfg.equipment.EquipmentTemplet;
import com.bbz.sanguo.cfg.equipment.EquipmentTempletCfg;
import com.bbz.util.db.IdentityObj;
import lombok.Data;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:43
 */

@Data
public class Equipment implements IdentityObj{

    /**
     * 唯一标识
     */
    private final long                  id;

    private final EquipmentTemplet      templet;
    private int                         level;

    public Equipment( long id, int templetId ){
        this.id = id;
        EquipmentTemplet templet1 = EquipmentTempletCfg.getEquipmentTempletById( templetId );
        this.templet = templet1;
    }


    public void addLevel( int addValue ){
        level += addValue;
    }
}
