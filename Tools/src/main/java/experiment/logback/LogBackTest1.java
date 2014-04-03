package experiment.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user         LIUKUN
 * time         2014-4-3 17:47
 */

public class LogBackTest1{
    static Logger logger = LoggerFactory.getLogger( LogBackTest1.class );
    static void record1(){
        logger.error( "来自LogBackTest1的记录" );
    }
}
