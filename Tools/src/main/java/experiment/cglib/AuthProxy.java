package experiment.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * user         LIUKUN
 * time         2014-8-19 17:57
 */

public class AuthProxy implements MethodInterceptor{

    private String name; // 会员登录名

    public AuthProxy( String name ){
        this.name = name;
    }

    @Override
    public Object intercept( Object o, Method method, Object[] args, MethodProxy methodProxy ) throws Throwable{
        if( !"maurice".equals( this.name ) ) {
            System.out.println( "AuthProxy:you have no permits to do manager!" );
            return null;
        }
//        System.out.println( method.getName() );
        return methodProxy.invokeSuper( o, args );
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }
}
