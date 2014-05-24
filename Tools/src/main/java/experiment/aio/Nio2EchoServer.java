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
    int count = 0;

    public static void main( String[] args ) throws IOException{
        new Nio2EchoServer().server( 8000 );
    }

    public void server( int port ) throws IOException{
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress( port );
        serverChannel.bind( address );

        final CountDownLatch latch = new CountDownLatch( 1 );
        System.out.println( Thread.currentThread().getName() + " call accept" );
        serverChannel.accept( null, new CompletionHandler<AsynchronousSocketChannel, Object>(){
            @Override
            public void completed( AsynchronousSocketChannel channel, Object attachment ){
                System.out.println( Thread.currentThread().getName() + " accept" );
                serverChannel.accept( null, this );
                try {
                    System.out.println( channel.getRemoteAddress() + "Total count is " + count++ );
                } catch( IOException e ) {
                    e.printStackTrace();
                }
                ByteBuffer buffer = ByteBuffer.allocate( 100 );
                channel.read( buffer, buffer, new EchoCompletionHandler( channel ) );
            }

            @Override
            public void failed( Throwable exc, Object attachment ){

                try {
                    serverChannel.close();
                } catch( IOException e ) {
                    //e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        } );
        try {
            latch.await();
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

        private final AsynchronousSocketChannel channel;

        public EchoCompletionHandler( AsynchronousSocketChannel channel ){
            this.channel = channel;
        }

        @Override
        public void completed( Integer result, ByteBuffer buffer ){
            //System.out.println( Thread.currentThread().getName() + " EchoCompletionHandler" );

            if( result == -1 ) {
                try {
                    channel.close();
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
            buffer.flip();
            System.out.println( buffer );

            channel.write( buffer, buffer, new CompletionHandler<Integer, ByteBuffer>(){
                @Override
                public void completed( Integer result, ByteBuffer buffer ){
                    if( buffer.hasRemaining() ) {
                        channel.write( buffer, buffer, this );
                    } else {
                        buffer.compact();
                        channel.read( buffer, buffer, EchoCompletionHandler.this );
                    }
                }

                @Override
                public void failed( Throwable exc, ByteBuffer attachment ){
                    try {
                        channel.close();
                    } catch( IOException e ) {

                    }
                }
            } );
        }

        @Override
        public void failed( Throwable exc, ByteBuffer attachment ){
            try {
                channel.close();
            } catch( IOException e ) {

            }
        }
    }
}

