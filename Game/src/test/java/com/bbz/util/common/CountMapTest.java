package com.bbz.util.common;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-23 16:01
 */

public class CountMapTest{
    @Test
    public void testPut() throws Exception{
        String name = "lk";
        CountMap<String> countMap = new CountMap<>();
        assertEquals( true, countMap.isEmpty() );
        assertEquals( 0, countMap.size() );
        countMap.put( name, 1 );
        countMap.clear();
        assertEquals( 0, countMap.size() );

        countMap.put( name, 3 );
        countMap.put( name, 20 );
        int count = countMap.get( name );
        assertEquals( 23, count );

        for( String s : countMap.keySet() ) {
            System.out.println( s );
        }

        for( Integer integer : countMap.values() ) {
            System.out.println( integer );
        }

        for( Map.Entry<String, Integer> entry : countMap.entrySet() ) {
            System.out.println( entry.getKey() + ":" + entry.getValue() );
        }

        assertEquals( true, countMap.containsValue( 23 ) );

        assertEquals( true, countMap.containsKey( name ) );

        countMap.remove( name );

    }

    @Test
    public void testPutAll() throws Exception{
        String name = "lk";
        Map<String, Integer> map = Maps.newHashMap();
        map.put( name, 100 );

        CountMap<String> countMap = new CountMap<>();
        countMap.put( name, 200 );
        countMap.putAll( map );
        int count = countMap.get( name );
        assertEquals( 300, count );
    }
}
