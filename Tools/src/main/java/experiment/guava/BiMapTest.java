package experiment.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午4:32
 * 测试双向Map，可以通过key查value，也可以通过value反查key
 * 特别要注意forcePut，可能会覆盖原有值
 */
public class BiMapTest{

    public static void main( String[] args ){
        BiMap<String, String> britishToAmerican = HashBiMap.create();
        britishToAmerican.put( "a", "A" );
        britishToAmerican.put( "b", "B" );
        System.out.println( britishToAmerican.size() );
        //britishToAmerican.put( "a", "B" );B在反查的时候会作为key出现，此时B(key)已经和b(value)关联起来了，所以会抛出异常
        britishToAmerican.forcePut( "a", "B" );//强制put可以插入，但是会覆盖掉[B,b]这条entry

        System.out.println( britishToAmerican.size() );
        System.out.println( britishToAmerican );

//
//
    }
}
