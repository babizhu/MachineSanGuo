package util.nosql;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * user         LIUKUN
 * time         2014-4-1 15:44
 */

public class NosqlUtilTest extends TestCase{
    public void testSet() throws Exception{
        long begin = System.nanoTime();
        for( int i = 0; i < 10000; i++ ) {

            //NosqlUtil.INSTANCE.set( "k", i );
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );


    }

    public void testGet() throws Exception{

    }

    @Test
    public void performanceTest() throws Exception{
        long begin = System.nanoTime();
        for( int i = 0; i < 100; i++ ) {

            NosqlUtil.INSTANCE.set( "k", i );
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );

    }


}
