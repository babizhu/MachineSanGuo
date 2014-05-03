package com.bbz.util.common;

import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * user         LIUKUN
 * time         2014-4-23 15:49
 * 一个计数器Map，用于计数，除了普通的Map方法外，额外增加了一个add方法
 * add的时候，如果某项值已经存在，则累加此值，不存在，则put此值，例如：
 * add( "小明的玩具", 1)
 * add( "小明的玩具", 3)
 * 如果是常规map,则map里面就是( "小明的玩具", 3)
 * 在此map中，则应该是( "小明的玩具", 4)，注意value的值
 * <p/>
 *
 * 如果key不存在则返回0
 * 如需同步，则需要另外一个同步map版本，此处暂时未处理
 */
@ToString
public class CountMap<K>{
    private Map<K, Integer> map = Maps.newHashMap();

    /**
     * 添加数据，如果map内部存在此key，则累加数据
     * @param key       key
     * @param change    change
     * @return          add之后的值
     */
    public Integer add( K key, int change ){
        int count = change;
        if( map.containsKey( key ) ) {
            count += map.get( key );
        }
        map.put( key, count );
        return count;
    }

    public void addAll( Map<K, Integer> m ){
        for( Map.Entry<? extends K, ? extends Integer> entry : m.entrySet() ) {
            add( entry.getKey(), entry.getValue() );
        }
    }

    public int size(){
        return map.size();
    }


    public Set<K> keySet(){
        return map.keySet();
    }


    public boolean isEmpty(){
        return map.isEmpty();
    }

    public void clear(){
        map.clear();
    }

    public Integer remove( K key ){
        return map.remove( key );
    }

    public Collection<Integer> values(){
        return map.values();
    }

    /**
     * 如果key不存在则返回0
     * @param key       要查询的key
     * @return          不存在key则返回0，否则返回相应的值
     */
    public Integer get( K key ){
        Integer count = map.get( key );
        return  count == null ? 0 : count;
    }


    public Integer put( K key, Integer value ){
        return map.put( key, value );
    }

    public boolean containsKey( K key ){
        return map.containsKey( key );
    }

    public Set<Map.Entry<K, Integer>> entrySet(){
        return map.entrySet();
    }
}
