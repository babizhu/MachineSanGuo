package experiment.xsocket.server;

import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-22 14:29
 */

public class EchoServer{
    public static void main( String[] args ) throws IOException{
        IServer server = new Server( "localhost", 8000, new EchoHandler() );
        server.start();
    }

}
