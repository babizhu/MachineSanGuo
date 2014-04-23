package com.bbz.util.common;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * user         LIUKUN
 * time         2014-4-23 15:49
 * 一个计数器Map，用于计数，
 * put的时候，如果某项值已经存在，则不像普通Map替换此值，而是累加此值，例如：
 * put( "小明的玩具", 1)
 * put( "小明的玩具", 3)
 * 如果是常规那么map里面就是( "小明的玩具", 3)
 * 在此map中，则应该是( "小明的玩具", 4)，注意value的值
 * <p/>
 * 如需同步，则需要另外一个同步map版本，此处暂时未处理
 */

public class CountMap<K>{
    private Map<K, Integer> map = Maps.newHashMap();

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    public int size(){
        return map.size();
    }


    public Set<K> keySet(){
        return map.keySet();
    }

    public boolean containsValue( Integer value ){
        //noinspection SuspiciousMethodCalls
        return map.containsValue( value );
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

    public Integer get( K key ){
        return map.get( key );
    }


    public Integer put( K key, int value ){
        int count = value;
        if( map.containsKey( key ) ) {
            count += map.get( key );
        }
        return map.put( key, count );
    }


    public boolean containsKey( K key ){
        return map.containsKey( key );
    }

    public Set<Map.Entry<K, Integer>> entrySet(){
        return map.entrySet();
    }


    public void putAll( Map<? extends K, ? extends Integer> m ){
        for( Map.Entry<? extends K, ? extends Integer> entry : m.entrySet() ) {
            put( entry.getKey(), entry.getValue() );
        }
    }
}
