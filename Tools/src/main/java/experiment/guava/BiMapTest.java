package experiment.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class BiMapTest{

    public static void main(String[] args){
        BiMap<String, String> britishToAmerican = HashBiMap.create();
        britishToAmerican.put("a", "A");
        britishToAmerican.put("b", "B");
        System.out.println(britishToAmerican.size());
        britishToAmerican.forcePut("a", "B");

        System.out.println(britishToAmerican.size());
        System.out.println(britishToAmerican);

        for( Map.Entry<String, String> entry : britishToAmerican.entrySet() ) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
//
//        britishToAmerican.put("aubergine", "eggplant");
//        britishToAmerican.put("courgette", "zucchini");
//        britishToAmerican.put("jam", "jelly");
//
//
//        System.out.println(britishToAmerican.get("aubergine")); // eggplant
//
//        BiMap<String, String> americanToBritish = britishToAmerican.inverse();
//
//        System.out.println(americanToBritish.get("eggplant")); // aubergine
//        System.out.println(americanToBritish.get("zucchini")); // courgette
    }
}
