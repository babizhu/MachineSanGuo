package experiment.xsocket.server;

import org.xsocket.connection.*;

import java.io.IOException;
import java.nio.BufferUnderflowException;

/**
 * user         LIUKUN
 * time         2014-4-22 14:29
 *
 * xSocket不保证一个客户端连接的多个请求仅被同一个线程处理，但是保证这些请求会被串行化处理，
 * 也就是说，一个请求没完成之前，此客户端的下一个请求会被阻塞
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
            try {
                Thread.sleep( 1000 );
            } catch( InterruptedException e ) {


            }
        }
        System.out.println( "处理线程为:" + Thread.currentThread().getName() );
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
