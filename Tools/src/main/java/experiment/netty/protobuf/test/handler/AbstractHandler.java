package experiment.netty.protobuf.test.handler;

import com.google.protobuf.GeneratedMessage;

/**
 * user         LIUKUN
 * time         2014-5-26 18:44
 */

public abstract class AbstractHandler<T extends GeneratedMessage>{

    public abstract GeneratedMessage run( T request );
}
