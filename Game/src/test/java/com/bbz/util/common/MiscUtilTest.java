package com.bbz.util.common;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-9 11:04
 */

public class MiscUtilTest{
    @Test
    public void testGetLevel() throws Exception{
        int[] expTolevel = new int[]{0, 10, 20, 50, 100};//经验到等级的数组
        int currentExp = 35;
        int level = MiscUtil.getLevel( expTolevel, currentExp, true );
        assertEquals( 3, level );
        level = MiscUtil.getLevel( expTolevel, currentExp, false );
        assertEquals( 2, level );

    }

    @Test
    public void testIsWindowsOS() throws Exception{
        System.out.println( System.getProperty( "os.name" ) );
        assertEquals( true, MiscUtil.isWindowsOS() );
    }

    @Test
    public void testSecondsToDateStr() throws Exception{
        int second = 1397013038;
        String str = MiscUtil.secondsToDateStr( second );
        System.out.println( str );
        assertEquals( "2014-04-09 11:10:38", str );

    }

    @Test
    public void testBufToString() throws Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate( 10 );
        byteBuffer.putInt( 512 );
        byteBuffer.put( (byte) 3 );
        String str = "ab";
        byteBuffer.put( str.getBytes() );

        str = "AB";
        byteBuffer.put( str.getBytes() );
//        byteBuffer.putChar( 'a' );java中的char是在内存中占用两个字节
//        byteBuffer.flip();不需要此步骤，内部会自行处理
        System.out.println( MiscUtil.bufToString( byteBuffer ) );

        for( int i = 0; i < 260; i++ ) {
            ByteBuffer byteBuffer1 = ByteBuffer.allocate( 10 );
            byteBuffer1.putInt( i );
            System.out.println( MiscUtil.bufToString( byteBuffer1 ) );
        }

    }

    /**
     * 字符串
     * @throws Exception
     */
    @Test
    public void testString() throws Exception{
        String str = "中国";
        System.out.println( "第一部分-------------------------------------------" );
        System.out.println( str.length() );
        System.out.println( str.getBytes().length );
        System.out.println( new String( str.getBytes(), "UTF-8" ).length() );
        System.out.println( new String( str.getBytes(), "UTF-8" ).getBytes().length );
        System.out.println( "--------------------------------------------------" );

        String str2 = "abc中国";
        System.out.println( "第二部分-------------------------------------------" );
        System.out.println( str2.length() );
        System.out.println( str2.getBytes().length );
        System.out.println( new String( str2.getBytes(), "UTF-8" ).length() );
        System.out.println( new String( str2.getBytes(), "UTF-8" ).getBytes().length );
        System.out.println( "--------------------------------------------------" );

        str2 = "abc中国";
        System.out.println( "第三部分-------------------------------------------" );
        System.out.println( str2.length() );
        System.out.println( str2.getBytes().length );
        System.out.println( new String( str2.getBytes(), "GBK" ).length() );
        System.out.println( new String( str2.getBytes(), "GBK" ).getBytes().length );
        System.out.println( "--------------------------------------------------" );

        str2 = "这里全部都是中国字";
        System.out.println( "第四部分-------------------------------------------" );
        System.out.println( str2.length() );
        System.out.println( str2.getBytes().length );
        System.out.println( new String( str2.getBytes(), "GBK" ).length() );
        System.out.println( new String( str2.getBytes(), "GBK" ).getBytes().length );
        System.out.println( "--------------------------------------------------" );

    }

}
