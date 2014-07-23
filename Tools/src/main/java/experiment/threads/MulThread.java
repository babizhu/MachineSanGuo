package experiment.threads;

/**
 * user         LIUKUN
 * time         2014-7-1 16:05
 */

public class MulThread{

    int a, b, x, y;

    public static void main( String[] args ) throws InterruptedException{
        new MulThread().run();
    }

    void run() throws InterruptedException{
        for( int i = 0; i < 100; i++ ) {
            Thread t1 = new Thread(){
                public void run(){
                    a = 1;
                    x = b;
                }
            };
            t1.start();
            t1.join();
        }

    }
}
