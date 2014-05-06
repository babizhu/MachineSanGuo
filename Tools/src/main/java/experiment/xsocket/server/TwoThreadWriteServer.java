package experiment.xsocket.server;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.*;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;

/**
 * user         LIUKUN
 * time         2014-5-6 11:38
 * 两个线程同时往同一个未同步的socket写数据会发生什么？
 * 结论
 * 在xsocket中，write函数最终调用了WriteQueue.append方法，而这个方法内部进行了同步，因此数据应该不会乱
 */

class WriteThread implements Runnable{

    private final INonBlockingConnection connection;

    WriteThread( INonBlockingConnection connection ){
        this.connection = connection;
    }

    @Override
    public void run(){
//        System.out.println( Thread.currentThread().getName() + "开始运行" + "{" + connection.getRemoteAddress() + "}");
        try {
            ByteBuffer buffer = ByteBuffer.allocate( 100 );
            for( int j = 0; j < 100; j++ ) {
                buffer.put( (byte) (j) );
            }
            buffer.flip();
            connection.write( buffer );
        } catch( IOException e ) {
            System.exit( 0 );
            e.printStackTrace();
        }

    }
}

class Handler implements IConnectHandler, IDataHandler{

    @Override
    public boolean onConnect( INonBlockingConnection connection ) throws IOException, BufferUnderflowException, MaxReadSizeExceededException{
        System.out.println( connection.getRemoteAddress() );
        return false;
    }

    @Override
    public boolean onData( INonBlockingConnection connection ) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException{
        Thread thread = new Thread( new WriteThread( connection ) );
        thread.start();

        ByteBuffer buffer = ByteBuffer.allocate( 100 );
        for( int j = 0; j < 100; j++ ) {
            buffer.put( (byte) (10 + j) );
        }
        buffer.flip();
        connection.write( buffer );

        return false;
    }
}

public class TwoThreadWriteServer{
    public static void main( String[] args ) throws IOException{
        IServer server = new Server( "0.0.0.0", 8000, new Handler() );
        server.start();
    }
}
