package experiment.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * user         LIUKUN
 * time         2014-5-12 17:18
 */

public class AFCDemo{
    static Thread current;

    public static void main( String[] args ) throws IOException, InterruptedException, ExecutionException{
//        if( args == null || args.length == 0 ) {
//            System.out.println( "Please input file path" );
//            return;
//        }
        System.out.println( Thread.currentThread().getName() );
        Path filePath = Paths.get( "f:FlashBuilder_4_6_LS10.exe" );
        AsynchronousFileChannel afc = AsynchronousFileChannel.open( filePath );
        ByteBuffer byteBuffer = ByteBuffer.allocate( 16000 * 1024 );
//使用FutureDemo时，请注释掉completionHandlerDemo，反之亦然
//        futureDemo( afc, byteBuffer );
        completionHandlerDemo( afc, byteBuffer );
    }

    private static void completionHandlerDemo( AsynchronousFileChannel afc, ByteBuffer byteBuffer ) throws IOException{
        current = Thread.currentThread();
        System.out.println( "current:" + current.getName() );
        afc.read( byteBuffer, 0, null, new CompletionHandler<Integer, Object>(){
            @Override
            public void completed( Integer result, Object attachment ){
                System.out.println( Thread.currentThread().getName() + ": Bytes Read = " + result );
                current.interrupt();
            }

            @Override
            public void failed( Throwable exc, Object attachment ){
                System.out.println( exc.getCause() );
                current.interrupt();
            }
        } );
        System.out.println( "Waiting for completion..." );
        try {
            current.join();
        } catch( InterruptedException e ) {
        }
        System.out.println( "End" );
        afc.close();
    }

    private static void futureDemo( AsynchronousFileChannel afc, ByteBuffer byteBuffer ) throws InterruptedException, ExecutionException, IOException{
        Future<Integer> result = afc.read( byteBuffer, 0 );
        while( !result.isDone() ) {
            System.out.println( "Waiting file channel finished...." );
            Thread.sleep( 1 );
        }
        System.out.println( "Finished? = " + result.isDone() );
        System.out.println( "byteBuffer = " + result.get() );
        System.out.println( byteBuffer );
        afc.close();
    }
}