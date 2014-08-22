package experiment.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * user         LIUKUN
 * time         2014-8-20 15:57
 * <p/>
 * 利用CGLIB动态创建多个方法来导致方法区溢出
 * 才创建5603个对象就挂了，我凑
 * 但我看到的异常和书里显示的有些不同
 */

public class JavaMethodAreaOOM{

    public static void main( String[] args ){
        int i = 0;
        while( true ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass( OOMObject.class );
            enhancer.setUseCache( false );
            enhancer.setCallback( new MethodInterceptor(){
                @Override
                public Object intercept( Object o, Method method, Object[] args, MethodProxy methodProxy ) throws Throwable{
                    return methodProxy.invokeSuper( o, args );
                }
            } );
            Object o = enhancer.create();
            System.out.println( "创建了" + i++ + "个" + o.getClass().getSimpleName() + "对象" );
        }

    }

    static class OOMObject{

    }
}
