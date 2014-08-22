package experiment.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-8-13 17:20
 */

public class JoinExp{

    /**
     * 把map的内容用指定的分隔符连接起来
     */
    private static void joinMap(){
        Map<Integer, String> map = Maps.newHashMap();
        for( int i = 0; i < 10; i++ ) {
            map.put( i, "id " + i );
        }

        Joiner.MapJoiner joiner = Joiner.on( "," ).withKeyValueSeparator( "->" );
        System.out.println( joiner.join( map ) );
    }

    public static void main( String[] args ){
        joinMap();
    }
}
