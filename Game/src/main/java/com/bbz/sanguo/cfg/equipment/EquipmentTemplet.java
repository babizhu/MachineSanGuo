package com.bbz.sanguo.cfg.equipment;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2014-12-25 17:56:07
 */
public class EquipmentTemplet {

    /**
	 * 装备id
	 */
    private final int id;



	/**
	 * 装备id
	 */
	public int getId() {
		return id;
	}/**
	 * 装备名称
	 */
    private final String name;



	/**
	 * 装备名称
	 */
	public String getName() {
		return name;
	}/**
	 * 穿戴等级
	 */
    private final int needLevel;



	/**
	 * 穿戴等级
	 */
	public int getNeedLevel() {
		return needLevel;
	}/**
	 * 资源格式
	 */
    private final String format;



	/**
	 * 资源格式
	 */
	public String getFormat() {
		return format;
	}/**
	 * 装备品阶
	 */
    private final int quality;



	/**
	 * 装备品阶
	 */
	public int getQuality() {
		return quality;
	}/**
	 * 装备类型（数据类型-1无 0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
	 */
    private final int additionType;



	/**
	 * 装备类型（数据类型-1无 0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
	 */
	public int getAdditionType() {
		return additionType;
	}/**
	 * 装备属性数值
	 */
    private final int baseAdditionValue;



	/**
	 * 装备属性数值
	 */
	public int getBaseAdditionValue() {
		return baseAdditionValue;
	}/**
	 * 装备强化成长数值
	 */
    private final float additionGrow;



	/**
	 * 装备强化成长数值
	 */
	public float getAdditionGrow() {
		return additionGrow;
	}/**
	 * 装备附加属性1（数据类型-1无 0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
	 */
    private final int additionType2;



	/**
	 * 装备附加属性1（数据类型-1无 0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）
	 */
	public int getAdditionType2() {
		return additionType2;
	}

	public EquipmentTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
needLevel = Integer.parseInt( element.getChildText("needLevel").trim() );
format = element.getChildText("format").trim();
quality = Integer.parseInt( element.getChildText("quality").trim() );
additionType = Integer.parseInt( element.getChildText("additionType").trim() );
baseAdditionValue = Integer.parseInt( element.getChildText("baseAdditionValue").trim() );
additionGrow = Float.parseFloat( element.getChildText("additionGrow").trim() );
additionType2 = Integer.parseInt( element.getChildText("additionType2").trim() );

	}

	@Override
	public String toString() {
		return "EquipmentTemplet [id = " + id + ",name = " + name + ",needLevel = " + needLevel + ",format = " + format + ",quality = " + quality + ",additionType = " + additionType + ",baseAdditionValue = " + baseAdditionValue + ",additionGrow = " + additionGrow + ",additionType2 = " + additionType2 + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
