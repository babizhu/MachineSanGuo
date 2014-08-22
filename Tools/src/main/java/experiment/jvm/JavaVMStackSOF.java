package experiment.jvm;

/**
 * user         LIUKUN
 * time         2014-8-19 16:11
 * 测试堆栈溢出
 */

public class JavaVMStackSOF{
    private int stackLength = 0;

    public static void main( String[] args ){
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
            stackSOF.stackLeak();
        } catch( Throwable e ) {

            System.out.println( "stack length：" + stackSOF.stackLength );
            throw e;
        }
    }

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
}
