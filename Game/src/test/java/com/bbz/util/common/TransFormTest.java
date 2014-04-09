package com.bbz.util.common;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-9 15:16
 * <p/>
 * Transform的测试用例
 */
@ToString
class Person{
    int age;
    String name;

    public Person( int age, String name ){
        this.age = age;
        this.name = name;
    }
}

public class TransformTest{

    @Test
    public void testArrayType() throws Exception{
        String str = "1,2,3,4,5,6,7";
        int[] array = Transform.ArrayType.toInts( str );
        assertEquals( 7, array.length );
        str = "";
        array = Transform.ArrayType.toInts( str );
        assertEquals( 0, array.length );
        try {
            Transform.ArrayType.toInts( null );
            str = ", ";
            Transform.ArrayType.toInts( str );
        } catch( NumberFormatException e ) {
            System.err.println( "输入字符串为无法转换为数字" );
        } catch( IllegalArgumentException e ) {
            System.err.println( "输入字符串为null" );
        }

    }

    @Test
    public void testStringType() throws Exception{
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        String str = Transform.StringType.toStr( array );
        assertEquals( "1,2,3,4,5,6,7", str );

        List<Integer> list = Ints.asList( array );
        str = Transform.StringType.toStr( list );
        assertEquals( "1,2,3,4,5,6,7", str );

        list = Lists.newArrayList();
        str = Transform.StringType.toStr( list );
        assertEquals( "", str );

        array = new int[0];
        str = Transform.StringType.toStr( array );
        assertEquals( "", str );
    }

    @Test
    public void testListType() throws Exception{
        String str = "";
        List<Integer> list = Transform.ListType.toList( str );
        assertEquals( 0, list.size() );

        try {
            Transform.ListType.toList( null );
            str = ", ";
            Transform.ListType.toList( str );
        } catch( NumberFormatException e ) {
            System.err.println( "输入字符串为无法转换为数字" );
        } catch( IllegalArgumentException e ) {
            System.err.println( "输入字符串为null" );
        }

        str = "1,2,3,4,5,6,7,8,9,10,11";
        list = Transform.ListType.toList( str );
        assertEquals( 11, list.size() );

        for( int i = 0; i < 10; i++ ) {
            List<Integer> copy = Lists.newArrayList( list );
            Transform.ListType.shuffle( copy );
            System.out.println( copy );
        }
    }

    /**
     * 测试guava的 newArrayList构造函数是深拷贝，还是浅拷贝
     * 结论对于String(不可变类),int等是深拷贝，对于常规的类是浅拷贝
     *
     * @throws Exception
     */
    @Test
    public void testListCopy() throws Exception{
        List<Person> list = Lists.newArrayList();
        for( int i = 0; i < 10; i++ ) {
            Person p = new Person( i, "bbz" );
            list.add( p );
        }
        ArrayList<Person> copy = Lists.newArrayList( list );
        copy.get( 0 ).age = 1001;
        System.out.println( copy );
        System.out.println( list );

        List<String> list1 = Lists.newArrayList( "1", "10", "100", "1000", "10000", "100000" );
        List<String> copy1 = Lists.newArrayList( list1 );
        copy1.set( 0, "30" );
        System.out.println( copy1 );
        System.out.println( list1 );

        List<Integer> list2 = Lists.newArrayList( 1,2,3,4,5,6,7,8 );
        List<Integer> copy2 = Lists.newArrayList( list2 );
        copy2.set( 0, 555 );
        System.out.println( copy2 );
        System.out.println( list2 );

    }
}
