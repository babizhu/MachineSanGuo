package experiment.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * user         LIUKUN
 * time         2014-5-21 16:45
 */

public class ByteBufTest{

    /**
     * 测试slice方法
     */
    public static void testSlice(){
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt( 23 );
        buf.writeByte( 10 );
        System.out.println( buf );
        ByteBuf slice = buf.slice( 4, 1 );
        System.out.println( buf );
        System.out.println( "slice:" + slice );
        byte b = slice.readByte();
        System.out.println( "b :" + b );

//        Unpooled.unreleasableBuffer( buf );
    }

    public static void main( String[] args ){
        testSlice();
    }

}
