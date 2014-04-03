package experiment.innerclass;

import java.io.IOException;

/**
 * Created by Administrator on 14-2-8.
 * 如何在A类当中使用B的内部类
 */
public class Test{

    public static void main(String[] args) throws IOException{
        new InnerClassDemo.StaticInnerClass().func1();

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().totalMemory());
        Runtime.getRuntime().exec("cd");
    }
}
