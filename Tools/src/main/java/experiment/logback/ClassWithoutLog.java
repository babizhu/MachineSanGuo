package experiment.logback;

/**
 * user         LIUKUN
 * time         2014-4-4 10:46
 * 如果这个类不带logger，那么他的异常好像会打印到控制台，不受日志记录的影响
 */

public class ClassWithoutLog{


    static void testWithoutLock(){
//        int i = 10 / 0;
    }
}
