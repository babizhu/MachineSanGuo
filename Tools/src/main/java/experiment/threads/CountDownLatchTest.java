package experiment.threads;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 14-2-11.
 */
public class CountDownLatchTest{
    private class Work implements Runnable{

        private final CountDownLatch countDownLatch;

        private Work(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run(){
            try {
                System.out.println(Thread.currentThread().getName() + " begin do Something!");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " end do Something!");
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
            countDownLatch.countDown();

        }
    }

    /**
     * 开启一个线程运行work
     *
     * @throws InterruptedException
     */
    void test() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Work work = new Work(countDownLatch);
//            Executors.defaultThreadFactory().newThread( work ).start();
        Thread thread = new Thread(work);
        thread.start();

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) throws InterruptedException{
        new CountDownLatchTest().test();
    }
}
