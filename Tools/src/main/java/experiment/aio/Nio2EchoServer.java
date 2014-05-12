package experiment.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * user         LIUKUN
 * time         2014/5/13 0013 2:29
 */

public class Nio2EchoServer{
    public void server( int port ) throws IOException{
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(  );
        InetSocketAddress address = new InetSocketAddress( port );
        serverChannel.bind( address );

        final CountDownLatch latch = new CountDownLatch( 1 );

        serverChannel.accept( null, new CompletionHandler<AsynchronousSocketChannel, Object>(){
            @Override
            public void completed( AsynchronousSocketChannel channel, Object attachment ){
                serverChannel.accept( null, this );
                ByteBuffer buffer = ByteBuffer.allocate( 100 );
                channel.read( buffer,buffer, new EchoCompletionHandler( channel) );
            }

            @Override
            public void failed( Throwable exc, Object attachment ){

            }
        });
        try {
            latch.await();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }




    }
    private final class EchoCompletionHandler implements CompletionHandler<Integer,ByteBuffer>{

        private final AsynchronousSocketChannel channel;

        public EchoCompletionHandler( AsynchronousSocketChannel channel ){
            this.channel = channel;
        }

        @Override
        public void completed( Integer result, ByteBuffer attachment ){

        }

        @Override
        public void failed( Throwable exc, ByteBuffer attachment ){

        }
    }
}

