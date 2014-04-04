package com.bbz.sanguo.cfg.corona;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2014-4-4 13:51:46
 */
public class CoronaTypeTemplet {

    /**
	 * 类型id
	 */
    private final int id;



	/**
	 * 类型id
	 */
	public int getId() {
		return id;
	}/**
	 * 类型名称
	 */
    private final String typeNeame;



	/**
	 * 类型名称
	 */
	public String getTypeNeame() {
		return typeNeame;
	}/**
	 * 权重
	 */
    private final int weight;



	/**
	 * 权重
	 */
	public int getWeight() {
		return weight;
	}/**
	 * 该类物品最大数量
	 */
    private final int numberMax;



	/**
	 * 该类物品最大数量
	 */
	public int getNumberMax() {
		return numberMax;
	}/**
	 * 获得该类物品权重
	 */
    private final int getWeight;



	/**
	 * 获得该类物品权重
	 */
	public int getGetWeight() {
		return getWeight;
	}

	public CoronaTypeTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
typeNeame = element.getChildText("typeNeame").trim();
weight = Integer.parseInt( element.getChildText("weight").trim() );
numberMax = Integer.parseInt( element.getChildText("numberMax").trim() );
getWeight = Integer.parseInt( element.getChildText("getWeight").trim() );

	}

	@Override
	public String toString() {
		return "CoronaTypeTemplet [id = " + id + ",typeNeame = " + typeNeame + ",weight = " + weight + ",numberMax = " + numberMax + ",getWeight = " + getWeight + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
