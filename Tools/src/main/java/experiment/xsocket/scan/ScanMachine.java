package experiment.xsocket.scan;

import com.google.common.collect.Sets;
import org.xsocket.connection.BlockingConnectionPool;
import org.xsocket.connection.IBlockingConnection;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * user         LIUKUN
 * time         2014-4-22 15:34
 */


public class ScanMachine{
    private static final int MAX_PORT = 65535;
    private static final int THREAD_COUNT = 2000;
    private static final int DURATION = MAX_PORT / THREAD_COUNT;
    private static final int SCAN_MSECOND = 2000;
    private Set<Integer> result = Sets.newHashSet();

    public static void main( String[] args ) throws InterruptedException{
//        BlockingConnectionPool pool = new BlockingConnectionPool();
//        pool.setMaxActive( 10 );
//        pool.getBlockingConnection( "localhost", 139, SCAN_MSECOND );

        long begin = System.nanoTime();
        new ScanMachine().doScan( "localhost" );
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
    }

    public void doScan( String host ) throws InterruptedException{
        ExecutorService service = Executors.newFixedThreadPool( THREAD_COUNT );
        for( int i = 0; i < THREAD_COUNT; i++ ) {
            service.execute( new Work( i, host ) );
        }
        service.shutdown();
        service.awaitTermination( 1000, TimeUnit.SECONDS );
        for( Integer integer : result ) {
            System.out.print( integer + "," );
        }
        System.out.println();
        System.out.println( "总共开启" + result.size() + "个端口！" );

    }

    class Work implements Runnable{
        private final String host;
        private final int min, max;
        private BlockingConnectionPool pool = new BlockingConnectionPool();


        public Work( int i, String host ){
            min = i * DURATION + 1;
            max = Math.min( MAX_PORT, (i + 1) * DURATION );
            System.out.println( Thread.currentThread().getName() + "min:" + min + "   max:" + max );
            this.host = host;
            pool.setMaxActive( 10 );
        }

        @Override
        public void run(){
            IBlockingConnection bc = null;
            for( int port = min; port <= max; port++ ) {
                try {
                    System.out.println( Thread.currentThread().getName() + "正在扫描:" + port );
                    bc = pool.getBlockingConnection( host, port, SCAN_MSECOND );
                    bc.close();
                    result.add( port );
                } catch( IOException e ) {
//                    result.put( port, false );
                    if( bc != null ) {
                        try {
                            pool.destroy( bc );
                        } catch( IOException e1 ) {
                            e1.printStackTrace();
                        }
                    }
                }

            }

        }

    }
}
