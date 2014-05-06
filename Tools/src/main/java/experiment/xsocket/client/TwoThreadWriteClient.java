package experiment.xsocket.client;

import org.xsocket.connection.BlockingConnectionPool;
import org.xsocket.connection.IBlockingConnection;
import org.xsocket.connection.MaxConnectionsExceededException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * user         LIUKUN
 * time         2014-5-6 13:24
 */

public class TwoThreadWriteClient{

    static boolean check( byte[] data ){
        for( int i = 0; i < 99; i++ ) {
            if( data[i] + 1 != data[i + 1] ) {
                return true;
            }
        }

        for( int i = 100; i < 199; i++ ) {
            if( data[i] + 1 != data[i + 1] ) {
                return true;
            }
        }
        return false;
    }

    public static void main( String[] args ){
        BlockingConnectionPool pool = new BlockingConnectionPool();
        pool.setMaxActive( 10 );

        IBlockingConnection bc = null;

        String host = "localhost";
        int port = 8000;
        try {
            bc = pool.getBlockingConnection( host, port, 3000 );
            while( true ) {
                bc.write( "a" );
                byte[] buf = new byte[200];
                for( int i = 0; i < 200; i++ ) {
                    buf[i] = bc.readByte();
//                    System.out.println( b );
                }
                if( check( buf ) ) {
                    for( int i = 0; i < 200; i++ ) {

//                    if( buf[i] == 66 ) {
//                        System.out.println( "66所在的位置" + i );
//                        break;
//                    }
                        System.out.print( buf[i] + " " );

                    }
                    System.out.println();
                }
            }
        } catch( SocketTimeoutException e ) {
            e.printStackTrace();
        } catch( MaxConnectionsExceededException e ) {
            e.printStackTrace();
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
}
