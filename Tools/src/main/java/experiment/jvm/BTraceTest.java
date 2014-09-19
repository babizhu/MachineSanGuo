package experiment.jvm;

/**
 * user         LIUKUN
 * time         2014-9-5 16:13
 * 测试BTrace功能
 */

public class BTraceTest{

    public static void main( String[] args ) throws InterruptedException{
        BTraceTest test = new BTraceTest();
        test.execute();
    }

    public void execute() throws InterruptedException{
        while( true ) {
            System.out.println( "a" );
            Thread.sleep( 1000 );
        }
    }
}
