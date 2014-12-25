package com.bbz.sanguo.cfg.equipment;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模版配置
 * @author liukun
 * 2014-12-25 17:56:07
 */
public class EquipmentTempletCfg {
	private static final Map<Integer,EquipmentTemplet> equipmentTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/equipment/equipment.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Equipment" );

			for (Object o : list) {
				EquipmentTemplet templet = new EquipmentTemplet( (Element) o );
				EquipmentTemplet temp = equipmentTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "EquipmentTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "EquipmentTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取EquipmentTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static EquipmentTemplet getEquipmentTempletById( int templetId ){
		return equipmentTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getEquipmentTempletById( id ) );
	}
}
