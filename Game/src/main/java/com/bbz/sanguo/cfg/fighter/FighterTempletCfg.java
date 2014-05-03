package com.bbz.sanguo.cfg.fighter;

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
 * 2014-5-1 0:05:28
 */
public class FighterTempletCfg {
	private static final Map<Integer,FighterTemplet> fighterTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/fighter/fighter.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Fighter" );

			for (Object o : list) {
				FighterTemplet templet = new FighterTemplet( (Element) o );
				FighterTemplet temp = fighterTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "FighterTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "FighterTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取FighterTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static FighterTemplet getFighterTempletById( int templetId ){
		return fighterTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getFighterTempletById( id ) );
	}
}
