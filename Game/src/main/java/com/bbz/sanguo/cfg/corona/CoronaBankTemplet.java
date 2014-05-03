package com.bbz.sanguo.cfg.corona;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2014-5-1 0:05:28
 */
public class CoronaBankTemplet {

    /**
	 * 顺序id
	 */
    private final int id;



	/**
	 * 顺序id
	 */
	public int getId() {
		return id;
	}/**
	 * 类型id
	 */
    private final int typeId;



	/**
	 * 类型id
	 */
	public int getTypeId() {
		return typeId;
	}/**
	 * 道具id
	 */
    private final int propId;



	/**
	 * 道具id
	 */
	public int getPropId() {
		return propId;
	}/**
	 * 道具名称
	 */
    private final String propName;



	/**
	 * 道具名称
	 */
	public String getPropName() {
		return propName;
	}/**
	 * 出现该权重
	 */
    private final int ariseWeight;



	/**
	 * 出现该权重
	 */
	public int getAriseWeight() {
		return ariseWeight;
	}/**
	 * 道具数量最小值
	 */
    private final int propMin;



	/**
	 * 道具数量最小值
	 */
	public int getPropMin() {
		return propMin;
	}/**
	 * 道具数量最大值
	 */
    private final int propMax;



	/**
	 * 道具数量最大值
	 */
	public int getPropMax() {
		return propMax;
	}/**
	 * 服务器获得该物品的最大数量（-1表示不限制数量）
	 */
    private final int getMax;



	/**
	 * 服务器获得该物品的最大数量（-1表示不限制数量）
	 */
	public int getGetMax() {
		return getMax;
	}

	public CoronaBankTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
typeId = Integer.parseInt( element.getChildText("typeId").trim() );
propId = Integer.parseInt( element.getChildText("propId").trim() );
propName = element.getChildText("propName").trim();
ariseWeight = Integer.parseInt( element.getChildText("ariseWeight").trim() );
propMin = Integer.parseInt( element.getChildText("propMin").trim() );
propMax = Integer.parseInt( element.getChildText("propMax").trim() );
getMax = Integer.parseInt( element.getChildText("getMax").trim() );

	}

	@Override
	public String toString() {
		return "CoronaBankTemplet [id = " + id + ",typeId = " + typeId + ",propId = " + propId + ",propName = " + propName + ",ariseWeight = " + ariseWeight + ",propMin = " + propMin + ",propMax = " + propMax + ",getMax = " + getMax + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
