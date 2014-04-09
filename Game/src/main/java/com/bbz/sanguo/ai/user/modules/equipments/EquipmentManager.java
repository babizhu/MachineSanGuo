package com.bbz.sanguo.ai.user.modules.equipments;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:52
 * 英雄管理类
 */

@Data
@ToString
public class EquipmentManager{

    final EquipmentDataProvider db;

    /**
     * 所有的英雄
     */
    private Map<Long,Equipment> equipments;

    public EquipmentManager( String uname ){
        db = new EquipmentDataProvider( uname );
        equipments = db.getMapAll();
    }

    /**
     * 装备升级
     *
     * @param equipmentId 要升级的英雄
     */
    public void levelUp( long equipmentId ){
        Equipment equipment = getEquipmentById( equipmentId );
        if( equipment == null ) {
            throw new ClientException( ErrorCode.EQUPMENT_NOT_FOUND, equipmentId + "不存在" );
        }
        //equipment.levelUp();
    }

    public Equipment getEquipmentById( long equipmentId ){
        return equipments.get( equipmentId );
    }




    public static void main( String[] args ){
    }


    /**
     * 添加一个道具
     * @param equipment
     */
    public void add( Equipment equipment ){
        equipments.put( equipment.getId(), equipment );
        db.add( equipment );
    }

    /**
     * 清除该玩家所有的道具
     */
    public void clear(){
        equipments.clear();
        db.removeAll();
    }

    public void addLevel( long equipmentId, int addValue ){
        Equipment equipment = equipments.get( equipmentId );
        if( equipment == null ){
            throw new ClientException( ErrorCode.EQUPMENT_NOT_FOUND, equipmentId + "不存在" );
        }
        equipment.addLevel( addValue );
        db.updateWithField( equipment, "level", equipment.getLevel() );

    }
}
