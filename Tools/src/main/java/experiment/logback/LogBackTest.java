package experiment.logback;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * user         LIUKUN
 * time         2014-4-3 15:29
 */

public class LogBackTest{
    static Logger logger = LoggerFactory.getLogger( LogBackTest.class );

    static void printSelfFunc(){
        for( StackTraceElement st : (new Throwable()).getStackTrace() ) {
//            if(classname.equals(st.getClassName()) || methods.contains(st.getMethodName())){
//                continue;
//            }else{
            System.out.println( st.getFileName() + ":" + st.getClassName() + ":" + st.getMethodName() + ":" + st.getLineNumber() );
//            }
        }

        logger.info( String.valueOf( new Throwable() ) );
    }

    public static void main( String[] args ){
        logger.info( "This is a 中文 log" );
        LogBackTest1.record1();

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //将内部状态进行输出
        //StatusPrinter.print( lc );
        ClassWithoutLog.testWithoutLock();


        printSelfFunc();
    }
}
