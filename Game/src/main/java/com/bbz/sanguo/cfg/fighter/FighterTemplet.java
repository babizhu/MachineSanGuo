package com.bbz.sanguo.cfg.fighter;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2014-4-5 12:54:34
 */
public class FighterTemplet {

    /**
	 * id
	 */
    private final int id;



	/**
	 * id
	 */
	public int getId() {
		return id;
	}/**
	 * 神将名字
	 */
    private final String name;



	/**
	 * 神将名字
	 */
	public String getName() {
		return name;
	}/**
	 * 初始生命
	 */
    private final int hp;



	/**
	 * 初始生命
	 */
	public int getHp() {
		return hp;
	}/**
	 * 初始攻击
	 */
    private final int phyattack;



	/**
	 * 初始攻击
	 */
	public int getPhyattack() {
		return phyattack;
	}/**
	 * 初始法强
	 */
    private final int magicAttack;



	/**
	 * 初始法强
	 */
	public int getMagicAttack() {
		return magicAttack;
	}/**
	 * 初始防御
	 */
    private final int phydefend;



	/**
	 * 初始防御
	 */
	public int getPhydefend() {
		return phydefend;
	}/**
	 * 初始法抗
	 */
    private final int magicDefend;



	/**
	 * 初始法抗
	 */
	public int getMagicDefend() {
		return magicDefend;
	}/**
	 * 初始速度
	 */
    private final int speed;



	/**
	 * 初始速度
	 */
	public int getSpeed() {
		return speed;
	}/**
	 * 初始暴击
	 */
    private final int crit;



	/**
	 * 初始暴击
	 */
	public int getCrit() {
		return crit;
	}/**
	 * 闪避
	 */
    private final int dodge;



	/**
	 * 闪避
	 */
	public int getDodge() {
		return dodge;
	}/**
	 * 格挡
	 */
    private final int block;



	/**
	 * 格挡
	 */
	public int getBlock() {
		return block;
	}/**
	 * 抗暴
	 */
    private final int unCrit;



	/**
	 * 抗暴
	 */
	public int getUnCrit() {
		return unCrit;
	}/**
	 * 命中
	 */
    private final int hit;



	/**
	 * 命中
	 */
	public int getHit() {
		return hit;
	}/**
	 * 破格
	 */
    private final int unBlock;



	/**
	 * 破格
	 */
	public int getUnBlock() {
		return unBlock;
	}/**
	 * 会心
	 */
    private final int critAddition;



	/**
	 * 会心
	 */
	public int getCritAddition() {
		return critAddition;
	}/**
	 * 法力值
	 */
    private final int magic;



	/**
	 * 法力值
	 */
	public int getMagic() {
		return magic;
	}/**
	 * 身价
	 */
    private final int social;



	/**
	 * 身价
	 */
	public int getSocial() {
		return social;
	}

	public FighterTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
hp = Integer.parseInt( element.getChildText("hp").trim() );
phyattack = Integer.parseInt( element.getChildText("phyattack").trim() );
magicAttack = Integer.parseInt( element.getChildText("magicAttack").trim() );
phydefend = Integer.parseInt( element.getChildText("phydefend").trim() );
magicDefend = Integer.parseInt( element.getChildText("magicDefend").trim() );
speed = Integer.parseInt( element.getChildText("speed").trim() );
crit = Integer.parseInt( element.getChildText("crit").trim() );
dodge = Integer.parseInt( element.getChildText("dodge").trim() );
block = Integer.parseInt( element.getChildText("block").trim() );
unCrit = Integer.parseInt( element.getChildText("unCrit").trim() );
hit = Integer.parseInt( element.getChildText("hit").trim() );
unBlock = Integer.parseInt( element.getChildText("unBlock").trim() );
critAddition = Integer.parseInt( element.getChildText("critAddition").trim() );
magic = Integer.parseInt( element.getChildText("magic").trim() );
social = Integer.parseInt( element.getChildText("social").trim() );

	}

	@Override
	public String toString() {
		return "FighterTemplet [id = " + id + ",name = " + name + ",hp = " + hp + ",phyattack = " + phyattack + ",magicAttack = " + magicAttack + ",phydefend = " + phydefend + ",magicDefend = " + magicDefend + ",speed = " + speed + ",crit = " + crit + ",dodge = " + dodge + ",block = " + block + ",unCrit = " + unCrit + ",hit = " + hit + ",unBlock = " + unBlock + ",critAddition = " + critAddition + ",magic = " + magic + ",social = " + social + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
