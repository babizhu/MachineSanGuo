package experiment.dynamiccompile;

import com.google.common.collect.Lists;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-11 11:56
 * 测试动态编译的模板
 */

public class DynamicCompileTest{

    public static String run( String o ){
        Integer i = 10;
        int i1 = 9;
        System.out.println( null instanceof Integer );
        if( o == null ) {
            return null;
        } else {
            return o;
        }
    }

    static void test(){
        String s = run( "aa" );
        if( s == null ) {
            System.out.println();
        }
    }

//    @Contract(value = "null -> null")

    public static void main( String[] args ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException{
        b.a.doSomething();
        System.out.println( -3 % 2 );
        Integer i1 = new Integer( 130 );
        Integer i2 = new Integer( 130 );
        System.out.println( i1 == i2 );
        System.out.println( i1.compareTo( i2 ) );

        Integer i3 = 220;
        Integer i4 = 220;
        System.out.println( "i3 == i4 :" + (i3 == i4) );
        //Java源代码
        String sourceStr = "public class Hello { public String sayHello(String name) { return\"Hello，\"+name+\"！\";}}";
        //类名及文件名
        String clsName = "Hello";
        //方法名
        String methodName = "sayHello";

        //当前编译器
        JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();

        //Java标准文件管理器
        StandardJavaFileManager fm = cmp.getStandardFileManager( null, null, null );

        //Java文件对象
        JavaFileObject jfo = new StringJavaObject( clsName, sourceStr );

        //编译参数，类似于javac＜options＞中的options
        List<String> optionsList = Lists.newArrayList();

        //编译文件的存放地方，注意：此处是为Eclipse工具特设的
        optionsList.addAll( Arrays.asList( "-d", "./Tools/target" ) );
//要编译的单元
        List<JavaFileObject> jfos = Arrays.asList( jfo );
//设置编译环境
        JavaCompiler.CompilationTask task = cmp.getTask( null, fm, null, optionsList, null, jfos );
//编译成功
        if( task.call() ) {
//生成对象
            Object obj = Class.forName( clsName ).newInstance();
            Class<? extends Object> cls = obj.getClass();
//调用sayHello方法
            Method m = cls.getMethod( methodName, String.class );
            String str = (String) m.invoke( obj, "Dynamic Compilation" );
            System.out.println( str );
        }
    }

    interface A{
        void doSomething();
    }

    interface b{
        static A a = new A(){


            @Override
            public void doSomething(){
                System.out.println( "接口里面也能有代码" );


            }
        };
    }
}

//文本中的Java对象
class StringJavaObject extends SimpleJavaFileObject{


    //源代码
    private String content = "";

    //遵循Java规范的类名及文件
    public StringJavaObject( String javaFileName, String content ){
        super( createStringJavaObjectUri( javaFileName ), Kind.SOURCE );
        this.content = content;
    }

    //产生一个URL资源路径
    private static URI createStringJavaObjectUri( String name ){
        //注意此处没有设置包名
        return URI.create( "String：///" + name + Kind.SOURCE.extension );
    }

    //文本文件代码
    @Override
    public CharSequence getCharContent( boolean ignoreEncodingErrors ) throws IOException{
        return content;
    }
}
