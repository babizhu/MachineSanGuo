package experiment.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * user         LIUKUN
 * time         2014-8-20 16:14
 * <p/>
 * 本机直接内存溢出
 */

public class DirectMemoryOOM{
    public static final int _1MB = 1024 * 1024;

    public static void main( String[] args ) throws IllegalAccessException{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible( true );
        Unsafe unsafe = (Unsafe) unsafeField.get( null );
        int i = 0;
        while( true ) {
            unsafe.allocateMemory( _1MB );
            System.out.println( "分配了" + i++ + "M内存" );
        }
    }
}
