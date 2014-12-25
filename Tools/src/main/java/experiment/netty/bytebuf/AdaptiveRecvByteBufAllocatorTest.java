package experiment.netty.bytebuf;

import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.RecvByteBufAllocator;

/**
 * user         LIUKUN
 * time         2014-10-10 17:06
 */

public class AdaptiveRecvByteBufAllocatorTest{
    public static void main( String[] args ){
        AdaptiveRecvByteBufAllocator allocator = AdaptiveRecvByteBufAllocator.DEFAULT;
        RecvByteBufAllocator.Handle handle = allocator.newHandle();
        handle.record( 10 );
        System.out.println( handle.guess() );
        handle.record( 1024 );
        System.out.println( handle.guess() );

        for (int i = 512; i > 0; i <<= 1) {
            System.out.println(i);
        }

        int m = 20003;
        System.out.println( m>>>1);
        System.out.println( m>>1);
        System.out.println( m/2);

        m = -20003;
        System.out.println( m>>>1);
        System.out.println( m>>1);
        System.out.println( m/2);
    }
}
