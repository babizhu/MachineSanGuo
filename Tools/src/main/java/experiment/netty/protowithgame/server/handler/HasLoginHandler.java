package experiment.netty.protowithgame.server.handler;

import static experiment.protocolgen.MsgProtocol.Request;
import static experiment.protocolgen.WorldClockProtocol.Location.Builder;

/**
 * user         LIUKUN
 * time         2014-6-6 11:56
 * <p/>
 * 登陆之后才可以执行的操作
 */
public interface HasLoginHandler{
    public abstract void run( Request request, Builder responseBuilder, String string );
}
