package experiment.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import experiment.serialize.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 14-2-13.
 * guava的集合排序例子
 */
public class SortCollection{

    private static final Comparator<Student> ageComparator = new Comparator<Student>(){
        @Override
        public int compare( Student o1, Student o2 ){
            return o1.getAge() - o2.getAge();
        }
    };

    private Comparator<Student> nameComparator = new Comparator<Student>(){
        @Override
        public int compare( Student o1, Student o2 ){
            return o1.getName().compareTo( o2.getName() );
        }
    };

    public static void main( String[] args ){
        new SortCollection().test4();
    }

    private List<Student> buildList(){
        int count = 10;
        List<Student> list = Lists.newArrayList();
        for( int i = 0; i < count; i++ ) {
            Student s = new Student();
            int age = new Random().nextInt( 100 );
            s.setAge( age );
            s.setName( "s" + new Random().nextInt( 14 ) );
            list.add( s );
        }
        System.out.println( list );
        return list;
    }

    /**
     * 返回一个排序后的list,和Collections.sort的区别在于不会改变原来的list
     */
    void test1(){

        List<Student> list = buildList();
        List<Student> orderList = Ordering.from( ageComparator ).compound( nameComparator ).sortedCopy( list );
        //Collections.sort( list, ageComparator );


        System.out.println( orderList );
    }

    /**
     * 手工完成如下功能：
     * 新生产一个list并排序，不改变原有list顺序
     */
    void test2(){
        List<Student> students = buildList();
        List<Student> copy = Lists.newArrayList( students );
        Collections.sort( copy, ageComparator );
        System.out.println( copy );
    }

    /**
     * 测试Ordering.arbitrary()的用途，尚不清楚
     */
    void test3(){
        List<Integer> list = Lists.newArrayList();
        for( int i = 0; i < 10; i++ ) {
//            Integer n = new Random().nextInt(100);
            list.add( i );
        }
        System.out.println( list );
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        System.out.println( "arbitraryOrdering:" + arbitraryOrdering.sortedCopy( list ) );
    }

    /**
     * 比较综合的测试
     */
    public void test4(){
        List<String> list = Lists.newArrayList( "abcd", "liukun" );
        list.add( "peida" );
        list.add( "jerry" );
        list.add( "harry" );
        list.add( "eva" );
        list.add( "jhon" );
        list.add( "neron" );

        System.out.println( "list:" + list );

        Ordering<String> naturalOrdering = Ordering.natural();
        System.out.println( "naturalOrdering:" + naturalOrdering.sortedCopy( list ) );

        List<Integer> listReduce = Lists.newArrayList();
        for( int i = 9; i > 0; i-- ) {
            listReduce.add( i );
        }

        List<Integer> listtest = Lists.newArrayList();
        listtest.add( 1 );
        listtest.add( 1 );
        listtest.add( 1 );
        listtest.add( 2 );


        Ordering<Integer> naturalIntReduceOrdering = Ordering.natural();


        System.out.println( "listtest:" + listtest );
        System.out.println( naturalIntReduceOrdering.isOrdered( listtest ) );
        System.out.println( naturalIntReduceOrdering.isStrictlyOrdered( listtest ) );


        System.out.println( "naturalIntReduceOrdering:" + naturalIntReduceOrdering.sortedCopy( listReduce ) );
        System.out.println( "listReduce:" + listReduce );


        System.out.println( naturalIntReduceOrdering.isOrdered( naturalIntReduceOrdering.sortedCopy( listReduce ) ) );
        System.out.println( naturalIntReduceOrdering.isStrictlyOrdered( naturalIntReduceOrdering.sortedCopy( listReduce ) ) );


        Ordering<String> natural = Ordering.natural();

        List<String> abc = ImmutableList.of( "a", "b", "c" );
        System.out.println( natural.isOrdered( abc ) );
        System.out.println( natural.isStrictlyOrdered( abc ) );

        System.out.println( "isOrdered reverse :" + natural.reverse().isOrdered( abc ) );

        List<String> cba = ImmutableList.of( "c", "b", "a" );
        System.out.println( natural.isOrdered( cba ) );
        System.out.println( natural.isStrictlyOrdered( cba ) );
        System.out.println( cba = natural.sortedCopy( cba ) );

        System.out.println( "max:" + natural.max( cba ) );
        System.out.println( "min:" + natural.min( cba ) );

        System.out.println( "leastOf:" + natural.leastOf( cba, 2 ) );
        System.out.println( "naturalOrdering:" + naturalOrdering.sortedCopy( list ) );
        System.out.println( "leastOf list:" + naturalOrdering.leastOf( list, 3 ) );
        System.out.println( "greatestOf:" + naturalOrdering.greatestOf( list, 3 ) );
        System.out.println( "reverse list :" + naturalOrdering.reverse().sortedCopy( list ) );
        System.out.println( "isOrdered list :" + naturalOrdering.isOrdered( list ) );
        System.out.println( "isOrdered list :" + naturalOrdering.reverse().isOrdered( list ) );
        list.add( null );
        System.out.println( " add null list:" + list );
        System.out.println( "nullsFirst list :" + naturalOrdering.nullsFirst().sortedCopy( list ) );
        System.out.println( "nullsLast list :" + naturalOrdering.nullsLast().sortedCopy( list ) );
    }

    /**
     * 测试Ordering.from( ageComparator ).sortedCopy 语句中的sortedCopy方法，拷贝的真实含义是什么
     * 结论是浅拷贝，并没有拷贝数据
     */
    void testSortedCopy(){
        List<Student> list = buildList();
        List<Student> orderList = Ordering.from( ageComparator ).sortedCopy( list );

        list.get( 0 ).setName( "xxxxxx" );
//        list.clear();
        System.out.println( list );
        System.out.println( orderList );

    }
}
