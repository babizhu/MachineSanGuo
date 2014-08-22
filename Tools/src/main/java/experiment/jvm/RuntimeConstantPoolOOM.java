package experiment.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-8-19 16:41
 * 测试常量溢出,jdk1.7好像不容易出状况，晚上跑个通宵看看
 */

public class RuntimeConstantPoolOOM{
    public static void main( String[] args ){
        String str1 = new StringBuilder( "计算机" ).append( "软件" ).toString();
        System.out.println( str1.intern() == str1 );

        String java = "java";
        System.out.println( "java".intern() == java );
        System.out.println( java.intern() == java );

        String str2 = new StringBuilder( "ja" ).append( "va" ).toString();
        System.out.println( str2.intern() == str2 );

        List<String> list = Lists.newArrayList();
        int i = 0;
        while( true ) {
            list.add( String.valueOf( i++ ).intern() );
            System.out.println( i );
        }
    }
}
