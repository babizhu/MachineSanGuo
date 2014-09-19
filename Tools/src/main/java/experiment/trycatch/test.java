package experiment.trycatch;

import java.util.zip.DataFormatException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 为什么明明把异常throw出去了，但main方法却捕捉不到呢？
 * 这是因为异常线程在监视到有异常发生时，就会登记当前的异常类型为DataFormatException，
 * 但是当执行器执行finally代码块时，则会重新为doStuff方法赋值，也就是告诉调用者“该方法执行正确，没有产生异常，返回值是1”，
 * 于是乎，异常神奇的消失了，如果屏蔽finally块代码，异常又会出现的
 */
public class test{

    public static <T extends Enum<T>> boolean Contain( Class<T> c, String name ){
        boolean result = false;
        try {
            Enum.valueOf( c, name );
            result = true;
        } catch( RuntimeException e ) {
//只要是抛出异常，则认为是不包含
        }
        return result;
    }


    static int buildCatch(){
        try {
            int i = 100;
            i = 10 / 0;
            System.out.println( "try" );
            return 6;
        } catch( Exception e ) {
            System.out.println( "catch" );
//            System.exit( 101 );
            return 8;

        } finally {
            System.out.println( "finally" );
            return 9;
        }
//        return 10;
    }

    static void tryFunc(){
        try {
            int i = -1;
            System.out.println( doSomething( i ) );
            System.out.println( doSomething( 10 ) );
        } catch( Exception e ) {
            e.printStackTrace();
            System.out.println( "test.tryFunc catch block " );
        }
    }

    private static int doSomething( int i ) throws DataFormatException{
        try {
            if( i < 0 ) {
                throw new DataFormatException( "数据格式错误" );
            } else {
                return i;
            }

        } catch( Exception e ) {//很奇怪，这个抛出的异常，外层捕获不到？，屏蔽了也捕获不了
            System.out.println( "test.doSomething" );
            throw e;
        } finally {
            return -10;
        }
    }

    public static void main( String[] args ){

        System.out.println( test.buildCatch() );

        tryFunc();

        System.out.println( Contain( aa.class, "ab" ) );

        int count = 0;
        for( int i = 0; i < 10; i++ ) {
            count = ++count;
        }
        System.out.println( count );
    }

    enum aa{a, b, c}
}
