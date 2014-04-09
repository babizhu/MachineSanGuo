package experiment.bytebuffer;

import java.nio.ByteBuffer;

/**
 * user         LIUKUN
 * time         2014-4-8 14:43
 */

public class ByteBufferTest{

    /**
     * ByteBuffer的duplicate会完全复制当前ByteBuffer的所有位置和内容信息，新老两个ByteBuffer共享数据
     */
    static void duplicateTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocate( 10 );
        byteBuffer.putInt( 3 );
        System.out.println( byteBuffer );
        ByteBuffer byteBuffer1 = byteBuffer.duplicate();
        System.out.println( byteBuffer1 );

        byteBuffer.flip();
        System.out.println( byteBuffer );
        System.out.println( byteBuffer1 );

        byteBuffer.clear();
        byteBuffer.putInt( 10 );
        byteBuffer.flip();
        byteBuffer1.flip();
        System.out.println( byteBuffer.getInt() );


    }

    /**
     * flip()+limit(byteBuffer.capacity()) 等价于 clear();
     */
    static void limitTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocate( 10 );
        System.out.println( byteBuffer );
        byteBuffer.putInt( 2 );
        System.out.println( byteBuffer );

        byteBuffer.flip();
        byteBuffer.limit( byteBuffer.capacity() );
        System.out.println(byteBuffer );
        byteBuffer.putInt( 43 );
        System.out.println(byteBuffer );
    }

    static void clearTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocate( 10 );
        System.out.println( byteBuffer );
        byteBuffer.putInt( 2 );
        System.out.println( byteBuffer );

//        byteBuffer.flip();
//        byteBuffer.limit( byteBuffer.capacity() );
        byteBuffer.clear();
        System.out.println(byteBuffer );
        byteBuffer.putInt( 43 );
        System.out.println(byteBuffer );
    }


    public static void main( String[] args ){
//        limitTest();
        clearTest();
    }
}
