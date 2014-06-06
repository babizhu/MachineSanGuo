package experiment.innerclass;

/**
 * Created by Administrator on 14-2-8.
 * 静态内部类的测试
 */
public class InnerClassDemo{

    private static int staticVar = 100;
    int var1;

    static void staticFunc(){
        System.out.println( "InnerClassDemo.staticFunc" );
    }

    public static void main( String[] args ){
        InnerClassDemo.StaticInnerClass.func2();
        new InnerClassDemo.StaticInnerClass().func1();

        InnerClassDemo.NonStaticInnerClass tool = new InnerClassDemo().new NonStaticInnerClass();
        tool.func1();
    }

    void f(){
        System.out.println();
    }

    static class StaticInnerClass{
        static int staticInnerVar1;//静态内部类可以拥有静态内部变量

        public static void func2(){

            //不能够从静态内部类的对象中访问外部类的非静态成员(包括成员变量与成员方法)
            //所以下面两句会出错
            //int n = var1;
            //f();

            //引用外部类的静态方法则没有问题，是否private完全无影响
            int n = staticVar;
            staticFunc();
        }

        void func1(){
//            int n = var1;
            System.out.println( "StaticInnerClass.func1()" );
        }
    }

    class NonStaticInnerClass{
        //static int n;// 非 静态内部类 不 可以拥有静态内部变量
        public void func1(){
            int n = var1;
            n = staticVar;
            staticFunc();

        }
    }
}
