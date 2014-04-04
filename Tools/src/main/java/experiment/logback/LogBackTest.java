package experiment.logback;

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
        ClassWithoutLog.testWithoutLock();
    }
}
