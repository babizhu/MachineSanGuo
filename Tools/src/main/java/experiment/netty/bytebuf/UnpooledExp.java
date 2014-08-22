package experiment.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * user         LIUKUN
 * time         2014-8-14 14:01
 * <p/>
 * 学习一下Unpooled.copiedBuffer()和Unpooled.wrappedBuffer的区别
 * 结论：
 * Unpooled.copiedBuffer(xx)的任何修改都和xx(源头)无关
 * Unpooled.wrappedBuffer的区别(xx)的任何修改都和xx(源头)有关
 */

public class UnpooledExp{
    static void wrappedBuffer(){
        byte[] content = {'a', 'b', 'c'};
        ByteBuf buf = Unpooled.wrappedBuffer( content );
        System.out.println( buf.getByte( 1 ) );

        content[1] = 'f';
        System.out.println( buf.getByte( 1 ) );

    }

    static void copiedBuffer(){
        byte[] content = {'a', 'b', 'c'};
        ByteBuf buf = Unpooled.copiedBuffer( content );
        System.out.println( buf.getByte( 1 ) );

        content[1] = 'f';
        System.out.println( buf.getByte( 1 ) );

    }

    public static void main( String[] args ){
        copiedBuffer();
        System.out.println( "-----------------------------------------" );
        wrappedBuffer();
    }
}
