package experiment.xsocket.server;

import org.xsocket.connection.*;

import java.io.IOException;
import java.nio.BufferUnderflowException;

/**
 * user         LIUKUN
 * time         2014-4-22 14:29
 */

public class EchoHandler implements IDataHandler, IConnectHandler, IDisconnectHandler, IIdleTimeoutHandler{

    @Override
    public boolean onConnect( INonBlockingConnection connection ) throws IOException, BufferUnderflowException{
        System.out.println( getClientName( connection ) + "Connect!" );
        return false;
    }


    @Override
    public boolean onData( INonBlockingConnection connection ) throws IOException, BufferUnderflowException{

        int available = connection.available();
        if( available > 0 ) {
            byte ch = connection.readByte();
            System.out.print( ch + " " );
            connection.write( ch );
        }
        return false;
    }


    @Override
    public boolean onDisconnect( INonBlockingConnection connection ) throws IOException{
        System.out.println( getClientName( connection ) + "Disconnect!" );
        return false;
    }


    @Override
    public boolean onIdleTimeout( INonBlockingConnection connection ) throws IOException{
        return false;
    }


    public String getClientName( INonBlockingConnection connection ){
        return connection.getRemoteAddress() + ":" + connection.getRemotePort() + " ";
    }
}
