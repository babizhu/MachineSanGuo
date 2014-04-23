package experiment.xsocket;

import com.google.common.collect.Maps;
import org.xsocket.connection.BlockingConnection;
import org.xsocket.connection.IBlockingConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-4-22 14:48
 */

public class BlockingConnectionTest{
    public static void main( String[] args ) throws IOException{
        String host = "192.168.1.1";
        int port = 8001;
        long begin = System.nanoTime();
        IBlockingConnection bc = null;
        Map<String, Object> options = Maps.newHashMap();
        options.put( "SO_TIMEOUT", 1000 );

        try {
            bc = new BlockingConnection( host, port, options );
        } catch( Exception e ) {

        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
        System.out.println( "end" );
    }

    static void test() throws IOException{
        String host = "192.168.1.1";
        int port = 8001;
        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress( host, port );
        socket.connect( address, 1000 );
    }
}
