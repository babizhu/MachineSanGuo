package experiment.fsl.templet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-18
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
public class MapTemplet {
    Multimap<Integer, MapNodeTemplet> mapData;//线路，线路上的所有节点

    /**
     * 通过json字符串构造
     *
     * @param jsonStr
     */
    public MapTemplet(String jsonStr) {
        Multimap<Integer, MapNodeTemplet> multimap = ArrayListMultimap.create();

//        URL url = Resources.getResource("1.json");
//        System.out.println( content );

        JSONArray paths = JSON.parseArray(jsonStr);
        for (Object o : paths) {
            JSONArray arr1 = JSON.parseArray(o.toString());
            for (Object o1 : arr1) {
                JSONObject jo = (JSONObject) o1;

                MapNodeTemplet node = new MapNodeTemplet(jo);

                if (node.getType() != 2) {//2:必须有内容的大石头
                    multimap.put(node.getPath(), node);
                }
//                if( node.getPath() < 3 ){//仅考虑主线，支线，方便我自己看
//                    mapNodes.add(node);
//                }


//                node.index = (JSONObject)o1.

//                System.out.println( o1 );
            }
        }
    }
}
