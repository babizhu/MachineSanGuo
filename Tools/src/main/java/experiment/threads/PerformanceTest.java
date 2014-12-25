package experiment.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * user         LIUKUN
 * time         2014-7-3 14:12
 * 用一个类似AtomInteger的类来比较lock，synchro关键字以及真实的AtomInteger的性能差距
 * 结论
 */

public class PerformanceTest{

    /**
     * 测试synchronized关键字的性能
     */
    public static void test1() throws InterruptedException{
        long constTime = 0;
        for( int j = 0; j < 10; j++ ) {


            int threadCount = 10;
            final int loopCount = 100000;
            long begin = System.nanoTime();
            Thread[] threads = new Thread[threadCount];
            final SynchroInteger si = new SynchroInteger();
            for( int i = 0; i < threadCount; i++ ) {
//                final LockInteger si = new LockInteger();         去掉注释，测试采用lock的方案
//                final AtomicInteger si = new AtomicInteger(  );   去掉注释，测试采用AtomicInteger的方案
                threads[i] = new Thread(){
                    @Override
                    public void run(){

                        for( int i = 0; i < loopCount; i++ ) {
                            si.incrementAndGet();
                        }


                    }
                };
            }

            for( int i = 0; i < threadCount; i++ ) {
                threads[i].start();
            }


            for( int i = 0; i < threadCount; i++ ) {
                threads[i].join();
            }
            constTime += (System.nanoTime() - begin);
//            if( si.get() != loopCount*threadCount ) {
//                System.out.println( "出错啦:" + si.get() );
//            }
        }

        System.out.println( "操作耗时：" + constTime / 1000000000f + "秒" );
        System.out.println( "总共生成了" + SynchroInteger.count + "个对象");
    }

    public static void main( String[] args ) throws InterruptedException{

        test1();
    }

    private static class SynchroInteger{
        static int count = 0;
        SynchroInteger(){
            count++;
        }
        private int i;

        synchronized int incrementAndGet(){
            i++;
            return i;
        }
        synchronized int get(){
            return i;
        }
    }

    private static class LockInteger{
        private Lock lock = new ReentrantLock();
        private int i;

        int incrementAndGet(){
            lock.lock();
            try {
                i++;
                return i;

            } finally {
                lock.unlock();
            }
        }

        int get(){
            lock.lock();
            try{
                return i;
            }
            finally {
                lock.unlock();
            }
        }
    }


}
