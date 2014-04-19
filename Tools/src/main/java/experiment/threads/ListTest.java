package experiment.threads;

/**
 * user         LIUKUN
 * time         2014-4-15 18:25
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试一个线程循环一个list的时候，另一个线程修改此list会出现什么情况
 * 结论
 * 如果采用ArrayList肯定会出问题,采用CopyOnWriteArrayList则不会有问题
 * 解决办法2加上synchronized关键字
 */

public class ListTest{
    List<Integer> list = new ArrayList<>();

    //    List<Integer> list = Lists.newCopyOnWriteArrayList();
    ListTest(){
        for( int i = 0; i < 10; i++ ) {
            list.add( i );
        }
    }

    public static void main( String[] args ){
        System.out.println( Thread.currentThread().getName() );
        new ListTest().test();
    }

    void test(){
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute( new Work1() );
        service.execute( new Work2() );
        service.shutdown();
    }

    class Work1 implements Runnable{

        @Override
        public void run(){
            synchronized( list ) {
                for( Integer integer : list ) {
                    System.out.println( Thread.currentThread().getName() + "\t" + integer );
                    try {
                        Thread.sleep( 50 );
                    } catch( InterruptedException e ) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Work2 implements Runnable{
        @Override
        public void run(){
            System.out.println( Thread.currentThread().getName() + "\t" + "Work2" );
            synchronized( list ) {
                list.add( 5 );
            }
        }
    }
}
