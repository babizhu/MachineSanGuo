package com.bbz.util.common;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;


/**
 * user         LIUKUN
 * time         2014-4-9 13:19
 */

public class RandomUtilTest{
    @Test
    public void testGetInt() throws Exception{
        System.out.println( RandomUtil.getInt( 100 ) );

    }

    @Test
    public void testGetInt1() throws Exception{
        int num;
        for( int i = 0; i < 100; i++ ) {

            num = RandomUtil.getInt( 1, 2 );
            assertEquals( 1, num );
        }
        Integer min = 2;
        int max = 10;
        for( int i = 0; i < 1000; i++ ) {

            num = RandomUtil.getInt( min, max );
            assertThat( num, Matchers.allOf( greaterThanOrEqualTo( min ), lessThan( max ) ) );
        }

    }



}
