package experiment.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * user         LIUKUN
 * time         2014-8-19 17:15
 * 通过反射自动生成代理类的测试程序
 * ISubject是接口
 * RealSubject是真实干活的类
 * ProxyHandler是用来动态生成代理类的关键代码
 * <p/>
 * 同一个ProxyHandler代理了两个不同的类【RealSubject】以及【LoginImpl】，这就是动态代理的意义
 * 不过这个动态代理是采用反射来运行的，效率方面略差
 */

public class Lanch{
    static public void main( String[] args ) throws Throwable{
        RealSubject rs = new RealSubject(); //在这里指定被代理类
        InvocationHandler ih = new ProxyHandler( rs );//代理实例的调用处理程序。
        //创建一个实现业务接口的代理类,用于访问业务类(见代理模式)。
        //返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序，如ProxyHandler。
        ISubject subject =
                (ISubject) Proxy.newProxyInstance( rs.getClass().getClassLoader(), rs.getClass().getInterfaces(), ih );
        //调用代理类方法,Java执行InvocationHandler接口的方法.
        subject.request();


        LoginImpl login = new LoginImpl();
        InvocationHandler ih1 = new ProxyHandler( login );//代理实例的调用处理程序。
        //注意：同一个ProxyHandler代理了两个不同的类【RealSubject】以及【LoginImpl】

        ILogin il = (ILogin) Proxy.newProxyInstance( login.getClass().getClassLoader(), login.getClass().getInterfaces(), ih1 );
        il.login( "xkl" );

    }
}