package com.bbz.sanguo.base.counter;

import com.bbz.util.time.SystemTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-28 17:42
 */

public class Counter12Test{
    @Test
    public void testGetCount() throws Exception{
        Counter12 counter = new Counter12( 20, SystemTimer.currentTimeSecond() );
        assertEquals( 20, counter.getCount() );

    }

    @Test
    public void testAddCount() throws Exception{
        Counter12 counter = new Counter12( 20, SystemTimer.currentTimeSecond() );
        counter.add( 5 );
        assertEquals( 25, counter.getCount() );
    }
}
