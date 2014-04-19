package experiment.sort;

import java.util.Arrays;

/**
 * user         LIUKUN
 * time         2014/4/13 0013 14:13
 */

public class ShellSort{

    public static void main( String[] args ){
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println( "排序之前：" );
        System.out.println( Arrays.toString( a ) );
//希尔排序
        int d = a.length;
        while( true ) {
            d = d / 2;
            for( int x = 0; x < d; x++ ) {
                for( int i = x + d; i < a.length; i = i + d ) {
                    int temp = a[i];
                    int j;
                    for( j = i - d; j >= 0 && a[j] > temp; j = j - d ) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
                System.out.println( Arrays.toString( a ) );
            }

            if( d == 1 ) {
                break;
            }
        }
        System.out.println();
        System.out.println( "排序之后：" );
        System.out.println( Arrays.toString( a ) );
    }
}

