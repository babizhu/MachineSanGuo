package experiment.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-8-19 15:25
 * 测试对象太多造成的溢出
 */

public class HeapOOM{
    public static void main( String[] args ){
        List<OOMObject> list = Lists.newArrayList();
        while( true ) {
            list.add( new OOMObject() );
        }

    }

    static class OOMObject{

    }
}
