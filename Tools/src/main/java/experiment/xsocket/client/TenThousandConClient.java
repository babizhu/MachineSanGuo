package experiment.xsocket.client;

import org.xsocket.connection.BlockingConnection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * user         LIUKUN
 * time         2014-5-6 17:04
 * 测试10000个连接连上来
 */

public class TenThousandConClient{
    private static final int MAX_COUNT = 1000;

    public static void main( String[] args ) throws IOException{
        new TenThousandConClient().run();
    }

    public void run() throws IOException{

        ExecutorService executorService = Executors.newCachedThreadPool();
        for( int i = 0; i < MAX_COUNT; i++ ) {
            executorService.execute( new Work() );
        }
    }

    class Work implements Runnable{

        public static final int CONNECTION_COUNT = 10;
        private final BlockingConnection con[];

        Work() throws IOException{
            con = new BlockingConnection[CONNECTION_COUNT];
            for( int i = 0; i < CONNECTION_COUNT; i++ ) {
                con[i] = new BlockingConnection( "localhost", 8000 );
            }

        }

        @Override
        public void run(){
            while( true ) {
                for( int i = 0; i < CONNECTION_COUNT; i++ ) {

                    try {

                        con[i].write( 3 );
                        byte ret = con[i].readByte();
                        try {
                            Thread.sleep( 10 );
                        } catch( InterruptedException e ) {
                            e.printStackTrace();
                        }
                    } catch( IOException e ) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
