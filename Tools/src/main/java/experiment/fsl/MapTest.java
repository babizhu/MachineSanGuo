package experiment.fsl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Resources;
import experiment.fsl.templet.MapNodeTemplet;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午2:43
 * 用fastJson做一些游戏地图方面的测试
 */


public class MapTest {

    Tree<Node> buildTree(Multimap<Integer, MapNodeTemplet> multimap) {


        Node head = Node.createHead();
        Tree<Node> tree = new Tree<Node>(head);
//        for (Map.Entry<Integer, MapNodeTemplet> enty : multimap.entries() ){
//
////            if (templet.getType() == 2) {
////                Node leaf = new Node(templet);
////                tree.addLeaf(leaf);
//            System.out.println( enty.getKey() + " : " + enty.getValue() );
//        }

        Collection<MapNodeTemplet> list = multimap.get(1);//主线
        for (MapNodeTemplet mapNodeTemplet : list) {
            Node leaf = new Node(mapNodeTemplet);
            tree.addLeaf(leaf);
        }


//
//        for( int i : multimap.keys() ){
//            Collection<MapNodeTemplet> list = multimap.get( i );
//
//        }
//        for( Map.Entry<Integer, Collection<MapNodeTemplet>> entries : multimap.asMap().entrySet() ){
//
//        }
        return tree;
    }

//    Tree<Node> buildTree( Collection<MapNodeTemplet> list ){
//        Node head = Node.createHead();
//        Tree<Node> tree = new Tree<Node>( list. );
//
//
//        return tree;
//    }

    public static void main(String[] args) throws IOException {
        //readJson();
        MapTest mapTest = new MapTest();
        Tree<Node> tree = mapTest.buildTree(mapTest.readJson());
        System.out.println(tree);
    }

    /**
     * Integer      路径id
     *
     * @return
     * @throws java.io.IOException
     */
    private Multimap<Integer, MapNodeTemplet> readJson() throws IOException {
        Multimap<Integer, MapNodeTemplet> multimap = ArrayListMultimap.create();

        URL url = Resources.getResource("1.json");
        String content = Resources.toString(url, Charset.defaultCharset());
//        System.out.println( content );

        JSONArray paths = JSON.parseArray(content);
        for (Object o : paths) {
            JSONArray arr1 = JSON.parseArray(o.toString());
            for (Object o1 : arr1) {
                JSONObject jo = (JSONObject) o1;

                MapNodeTemplet node = new MapNodeTemplet(jo);


                if (node.getType() != 2) {
                    multimap.put(node.getPath(), node);
                }
//                if( node.getPath() < 3 ){//仅考虑主线，支线，方便我自己看
//                    mapNodes.add(node);
//                }


//                node.index = (JSONObject)o1.

//                System.out.println( o1 );
            }
        }
//        System.out.println(paths.size());
//        System.out.println( mapNodes );
//        for (MapNodeTemplet mapNode : mapNodes) {
////            System.out.println( mapNode );
//        }
//        System.out.println(mapNodes.size());
        System.out.println(multimap);
        return multimap;
    }

//    boolean move()
}
