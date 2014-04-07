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
    public static void main( String[] args ){
        logger.info("This is a 中文 log");
        LogBackTest1.record1();

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //将内部状态进行输出
        //StatusPrinter.print( lc );
        ClassWithoutLog.testWithoutLock();
    }
}
