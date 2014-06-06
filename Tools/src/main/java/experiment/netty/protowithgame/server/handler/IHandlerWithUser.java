package experiment.netty.protowithgame.server.handler;

import static experiment.protocolgen.MsgProtocol.Request;
import static experiment.protocolgen.MsgProtocol.Response.Builder;

/**
 * user         LIUKUN
 * time         2014-6-6 19:05
 */
public interface IHandlerWithUser extends IHandler{
    public abstract void run( Request request, Builder responseBuilder, Object user );
}
