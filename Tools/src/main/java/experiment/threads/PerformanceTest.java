package experiment.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * user         LIUKUN
 * time         2014-7-3 14:12
 * 用一个类似AtomInteger的类来比较lock，synchro关键字以及真实的AtomInteger的性能差距
 */

public class PerformanceTest{

    /**
     * 测试synchronized关键字的性能
     */
    public static void test1() throws InterruptedException{
        long constTime = 0;
        for( int j = 0; j < 10; j++ ) {


            int threadCount = 10;
            long begin = System.nanoTime();
            Thread[] threads = new Thread[threadCount];
            for( int i = 0; i < threadCount; i++ ) {
                final SynchroInteger si = new SynchroInteger();
                final LockInteger si1 = new LockInteger();
                threads[i] = new Thread(){
                    @Override
                    public void run(){
                        int loopCount = 100000;
                        for( int i = 0; i < loopCount; i++ ) {
                            si.incrementAndGet();
                        }
                        if( si.i != loopCount ) {
                            System.out.println( "出错啦:" + si.i );
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
        }

        System.out.println( "操作耗时：" + constTime / 1000000000f + "秒" );
    }

    public static void main( String[] args ) throws InterruptedException{

        test1();
    }

    private static class SynchroInteger{
        private int i;

        synchronized int incrementAndGet(){
            i++;
            return i;
        }
    }

    private static class LockInteger{
        private Lock lock = new ReentrantLock();
        private int i;

        synchronized int incrementAndGet(){
            lock.lock();
            try {
                i++;
                return i;

            } finally {
                lock.unlock();
            }
        }
    }


}
