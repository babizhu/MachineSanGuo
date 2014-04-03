package experiment.serialize;


import org.msgpack.annotation.Message;

/**
 * 模版
 *
 * @author liukun
 *         2013-11-16 0:06:02
 */

@Message
public class NpcTemplet{

    /**
     * id
     */
    private  int id = 5;


    /**
     * id
     */
    public int getId(){
        return id;
    }

    /**
     * 名称
     */
    private  String name = "abcd";


    /**
     * 名称
     */
    public String getName(){
        return name;
    }

    /**
     * 描述
     */
    private  String desc = "def";


    /**
     * 描述
     */
    public String getDesc(){
        return desc;
    }

    /**
     * 生命基础值
     */
    private  int hpBase = 34;


    /**
     * 生命基础值
     */
    public int getHpBase(){
        return hpBase;
    }

    /**
     * 物攻
     */
    private  int phyAttackBase;


    /**
     * 物攻
     */
    public int getPhyAttackBase(){
        return phyAttackBase;
    }

    /**
     * 物防
     */
    private  int phyDefendBase = 45;


    /**
     * 物防
     */
    public int getPhyDefendBase(){
        return phyDefendBase;
    }

    /**
     * 魔攻
     */
    private  int magicAttackBase = 90;


    /**
     * 魔攻
     */
    public int getMagicAttackBase(){
        return magicAttackBase;
    }

    /**
     * 魔防
     */
    private  int magicDefendBase;


    /**
     * 魔防
     */
    public int getMagicDefendBase(){
        return magicDefendBase;
    }

    /**
     * 速度
     */
    private  int speed = 9898;


    /**
     * 速度
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * 躲避
     */
    private  int dodge = 78;


    /**
     * 躲避
     */
    public int getDodge(){
        return dodge;
    }

    /**
     * 暴击
     */
    private  int crit = 65;


    /**
     * 暴击
     */
    public int getCrit(){
        return crit;
    }

    /**
     * 暴击倍数
     */
    private  int critMultiple;


    /**
     * 暴击倍数
     */
    public int getCritMultiple(){
        return critMultiple;
    }

    /**
     * 技能
     */
    private  int skillTempletId;


    /**
     * 技能
     */
    public int getSkillTempletId(){
        return skillTempletId;
    }

    /**
     * 掉落物品
     */
    private String dropItem;


    /**
     * 掉落物品
     */
    public String getDropItem(){
        return dropItem;
    }


    public NpcTemplet(){


    }

    @Override
    public String toString(){
        return "NpcTemplet [id = " + id + ",name = " + name + ",desc = " + desc + ",hpBase = " + hpBase + ",phyAttackBase = " + phyAttackBase + ",phyDefendBase = " + phyDefendBase + ",magicAttackBase = " + magicAttackBase + ",magicDefendBase = " + magicDefendBase + ",speed = " + speed + ",dodge = " + dodge + ",crit = " + crit + ",critMultiple = " + critMultiple + ",skillTempletId = " + skillTempletId + ",dropItem = " + dropItem + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
