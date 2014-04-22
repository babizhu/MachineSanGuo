package experiment.xsocket.base;

import org.junit.Assert;
import org.junit.Test;
import org.xsocket.DataConverter;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 * user         LIUKUN
 * time         2014-4-23 14:10
 */

public class ReadQueueTest{
    private static void read( ReadQueue readQueue, int run ) throws IOException{
        readText( readQueue, run );
        readInt( readQueue );
    }

    private static void readText( ReadQueue readQueue, int run ) throws IOException{

        while( true ) {
            try {
                ByteBuffer[] buffer = readQueue.readByteBufferByDelimiter( ";".getBytes( "UTF-8" ), Integer.MAX_VALUE );
                String msg = DataConverter.toString( buffer );
//                System.out.println( msg );
                Assert.assertEquals( "[run " + run + "] msg is not TEST it is " + msg, "TEST", msg );
                return;
            } catch( BufferUnderflowException ignored ) {
            }
        }
    }

    private static void readInt( ReadQueue readQueue ) throws IOException{
        while( true ) {
            try {
                ByteBuffer[] data = readQueue.readByteBufferByLength( 4 );
                int i = DataConverter.toByteBuffer( data ).getInt();
//                System.out.println( "i:" + i );
                Assert.assertEquals( 5, i );
                return;
            } catch( BufferUnderflowException ignored ) {
            }
        }
    }

    private static void insert( int count, ReadQueue readQueue ){
        for( int i = 0; i < count; i++ ) {
            ByteBuffer buf = DataConverter.toByteBuffer( "TEST", "UTF-8" );
            readQueue.append( new ByteBuffer[]{buf}, buf.limit() );
            ByteBuffer buf2 = DataConverter.toByteBuffer( ";", "UTF-8" );
            readQueue.append( new ByteBuffer[]{buf2}, buf2.limit() );
            ByteBuffer buf3 = DataConverter.toByteBuffer( 5 );
            readQueue.append( new ByteBuffer[]{buf3}, buf3.limit() );
        }
    }

    @Test
    public void testRead() throws Exception{
        ReadQueue readQueue = new ReadQueue();

        int count = 1000;
        insert( count, readQueue );

        int read = 0;
        do {

            read( readQueue, read );
            read++;

        } while( read < count );
    }

    @Test
    public void testFind() throws Exception{
        byte[] delimiter = new byte[]{0x0d, 0x0a, 0x0d, 0x0a};
        ByteBuffer input = ByteBuffer.wrap( new byte[]{0x36, 0x2e, 0x0d, 0x0a, 0x31, 0x2e, 0x78, 0x29} );
        ByteBuffer input2 = ByteBuffer.wrap( new byte[]{0x0d, 0x0a, 0x0d, 0x0a, 0x74, 0x72, 0x75, 0x65} );

        ReadQueue readQueue = new ReadQueue();
        readQueue.append( new ByteBuffer[]{input}, input.limit() );


        try {
            readQueue.readByteBufferByDelimiter( delimiter, Integer.MAX_VALUE );
        } catch( BufferUnderflowException ignored ) {
        }

        readQueue.append( new ByteBuffer[]{input2}, input2.limit() );
        ByteBuffer[] buffers = readQueue.readByteBufferByDelimiter( delimiter, Integer.MAX_VALUE );

        String s1 = DataConverter.toString( input.duplicate() );
        String s2 = DataConverter.toString( buffers );

        Assert.assertEquals( s1, s2 );
    }

    @Test
    public void testConcurrent() throws Exception{
        ReadQueue readQueue = new ReadQueue();

        int count = 10000;

        Thread t = new Thread( new Writer( readQueue, count ) );
        t.start();

        int read = 0;
        do {

            read( readQueue, read );
            read++;

        } while( read < count );
    }

    private static final class Writer implements Runnable{

        private ReadQueue readQueue = null;
        private int count = 0;

        public Writer( ReadQueue readQueue, int count ){
            this.readQueue = readQueue;
            this.count = count;
        }

        public void run(){
            try {
                insert( count, readQueue );

            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }
}
