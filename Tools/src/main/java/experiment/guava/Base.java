package experiment.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import experiment.serialize.Student;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午4:37
 * 基础内容
 */

public class Base{
    static void create(){
        List<String> list = new ArrayList<String>();
        list.add( "1" );
        list.add( "2" );


        List<String> list1 = Lists.newArrayList( "1", "3", "2" );
        Set<String> set = Sets.newTreeSet( list1 );
        System.out.println( set );
    }

    /**
     * 不可变的list，map，注意这里的不可变指的是map或者list本身，并不包括里面的类
     */
    static void immutable(){

        ImmutableMap<Integer, String> map = ImmutableMap.of(
                1, "1",
                3, "3",
                4, "4",
//                6, "6",不屏蔽就会出错
                5, "5",
                2, "2"
        );

        Point point = new Point( 100, 100 );
        List<Point> list1 = ImmutableList.of( point );
        Point point1 = list1.get( 0 );
        point1.setLocation( 200, 200 );

        System.out.println( list1.get( 0 ) );


        List<Integer> list = ImmutableList.of( 1, 2, 3 );
        list.add( 3 );//抛异常
    }

    private static void setsTest(){
        HashSet<Integer> setA = Sets.newHashSet( 1, 2, 3, 4, 5 );
        HashSet<Integer> setB = Sets.newHashSet( 4, 5, 6, 7, 8 );

        Sets.SetView<Integer> union = Sets.union( setA, setB );
        System.out.println( "union:" );
        for( int i : union )
            System.out.println( i );

        Sets.SetView<Integer> difference = Sets.difference( setA, setB );
        System.out.println( "difference:" );
        for( Integer integer : difference )
            System.out.println( integer );

        Sets.SetView<Integer> intersection = Sets.intersection( setA, setB );
        System.out.println( "intersection:" );
        for( Integer integer : intersection )
            System.out.println( integer );
    }

    public static void multimapTest(){
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        multimap.put( 1, "a" );
        multimap.put( 1, "b" );
        multimap.put( 1, "c" );
        multimap.put( 1, "cd" );
        multimap.put( 2, "a" );
        multimap.put( 2, "b" );
        multimap.put( 2, "c" );

        for( String s : multimap.values() ) {
            System.out.print( s );
            System.out.print( "-" );
        }


        List<String> list2 = Lists.newArrayList( multimap.get( 1 ) );
//        multimap.
//        multimap.asMap().
        Collection<String> list = multimap.get( 1 );
//        list.get
        System.out.println( list2 );
        System.out.println( "multimap的数量" + multimap.size() );
        System.out.println( "multimap中key为1的数量" + multimap.get( 1 ).size() );
//        Multimap<Integer,String> integerStringMultimap = LinkedL


    }


    public static void tableTest(){
        Table<Integer, Integer, Student> studentTable = HashBasedTable.create();//年级，班级，学生
        Student student = new Student( "liukun", 32 );
        Student student1 = new Student( "liukun", 132 );

        studentTable.put( 1, 1, student );
        studentTable.put( 1, 3, student1 );

        Student s = studentTable.get( 1, 1 );
        System.out.println( s );

        Map<Integer, Student> rowMap = studentTable.row( 1 );   //得到行集合 ,一年级所有的班
        Map<Integer, Student> colMap = studentTable.column( 3 );   //得到列集合，所有年级的三班
        System.out.println( rowMap );
        System.out.println( colMap );
        System.out.println( studentTable );

//        Table<Integer,Integer,List<Student>> s1 = HashBasedTable.create();//年级，班级，学生们
//        s1.put(1, 1, student);

    }

    /**
     * 各种插入遍历的顺序
     * LinkedListMultimap是严格按照插入顺序排序的
     * ArraylistMultimap则不是
     */
    public static void multimapForeach(){
        Multimap<Integer, String> multimap = LinkedListMultimap.create();
        multimap.put( 1, "ba" );
        multimap.put( 2, "2a" );
        multimap.put( 4, "4a" );
        multimap.put( 3, "3a" );
        multimap.put( 21, "21a" );
        multimap.put( 12, "12a" );
        multimap.put( 1, "fd" );
        multimap.put( 1, "a" );

        for( String s : multimap.values() ) {
            System.out.println( s );
        }


        for( int i : multimap.keys() ) {
            System.out.println( i );
        }

        System.out.println( "------------------------------" );
        for( int i : multimap.keySet() ) {
            System.out.println( i );
        }

        List<String> list1 = (List<String>) multimap.values();
        System.out.println( "values11111111111111:" + list1.get( 4 ) );
    }

    public static void StringTest(){
        int[] numbers = {1, 2, 3, 4, 5};

        String numbersAsString = Joiner.on( ";" ).join( Ints.asList( numbers ) );
        System.out.println( numbersAsString );

        String numbersAsStringDirectly = Ints.join( ";", numbers );
        System.out.println( numbersAsStringDirectly );

        String testString = "foo , what,,,more,";
        Iterable<String> split = Splitter.on( "," ).omitEmptyStrings().trimResults().split( testString );

        Iterator<String> iter = split.iterator();
        while( iter.hasNext() ) {
            System.out.println( iter.next() );
        }

        System.out.println( split );

        List<String> list = Lists.newArrayList();
        for( int i = 0; i < 100; i++ ) {
            list.add( i + "" );
        }
        Iterator<String> iterator = list.iterator();
        while( iterator.hasNext() ) {
            System.out.println( iterator.next() );
        }


    }

    public static void main( String[] args ){
        create();
        immutable();
        multimapForeach();
//        Immutable();
//        StringTest();
//
//        setsTest();
//        multimapTest();
//
//        tableTest();
    }
}
