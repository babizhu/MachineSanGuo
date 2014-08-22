package experiment.jvm;

/**
 * user         LIUKUN
 * time         2014-8-20 16:44
 * 确认正在使用的jvm是否采用了引用计数的方法来确定 对象是否能清除
 */

public class ReferenceCountingGC{
    private static final int _1MB = 1024 * 102;
    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];
    public Object instance = null;

    public static void testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        //假设在这行发生GC,objA和objB是否能被回收？
        System.gc();
    }

    public static void main( String[] args ){
        testGC();
    }
}