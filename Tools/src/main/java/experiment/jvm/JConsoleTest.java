package experiment.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-4 18:11
 * 利用jconsol来观察程序内存的变化
 */

public class JConsoleTest{
    public static void fillHeap( int num ) throws InterruptedException{
        List<OOMObject> list = Lists.newArrayList();
        for( int i = 0; i < num; i++ ) {
//稍作延时，令监视曲线的变化更加明显
            Thread.sleep( 50 );
            list.add( new OOMObject() );
        }
        System.gc();
    }

    public static void main( String[] args ) throws InterruptedException{
        fillHeap( 1000 );
    }

    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

}
