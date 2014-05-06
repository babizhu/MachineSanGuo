package experiment.xsocket.server;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.*;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * user         LIUKUN
 * time         2014-5-6 16:59
 */

class TenThousandConHandler implements IDataHandler, IConnectHandler, IConnectionTimeoutHandler, IDisconnectHandler{
    AtomicInteger counter = new AtomicInteger( 0 );

    @Override
    public boolean onConnect( INonBlockingConnection connection ) throws IOException, BufferUnderflowException, MaxReadSizeExceededException{
        System.out.println( getClientName( connection ) + " is connected!Total count " + counter.addAndGet( 1 ) );
        return true;
    }

    @Override
    public boolean onData( INonBlockingConnection connection ) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException{

        byte b = connection.readByte();
        connection.write( b );
        return false;
    }

    private String getClientName( INonBlockingConnection con ){
        return con.getRemoteAddress() + " " + con.getRemotePort();
    }

    @Override
    public boolean onConnectionTimeout( INonBlockingConnection connection ) throws IOException{
        System.out.println( getClientName( connection ) + " is timeout! Total count " + counter.decrementAndGet() );
        return false;
    }

    @Override
    public boolean onDisconnect( INonBlockingConnection connection ) throws IOException{
        System.out.println( getClientName( connection ) + " is disconnect! Total count " + counter.decrementAndGet() );
        return false;
    }
}

public class TenThousandConServer{
    public static void main( String[] args ) throws IOException{
        IServer server = new Server( "localhost", 8000, new TenThousandConHandler() );
        server.start();
    }
}
