package experiment.xsocket.client;

import org.xsocket.connection.BlockingConnectionPool;
import org.xsocket.connection.IBlockingConnection;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-22 14:27
 * xsocket客户端的编写和学习
 */

public class BlockingConnectionPoolTest{
    public static void main( String[] args ){

        BlockingConnectionPool pool = new BlockingConnectionPool();
        pool.setMaxActive( 10 );

        IBlockingConnection bc = null;

        String host = "localhost";
        int port = 8000;
        long begin = System.nanoTime();
        try {
            bc = pool.getBlockingConnection( host, port, 3000 );
            bc.write( "Hello" );
            bc.write( "刘老爷\r\n" );

            Thread.currentThread().sleep( 50000 );
            bc.close();
        } catch( IOException e ) {
            System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
//            e.printStackTrace();
            if( bc != null ) {
                try {
                    pool.destroy( bc );
                    System.out.println( "pool.destroy( bc );" );
                } catch( IOException e1 ) {

                }
            }
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
    }

}
