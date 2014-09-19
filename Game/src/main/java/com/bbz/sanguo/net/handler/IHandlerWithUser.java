package com.bbz.sanguo.net.handler;


import static com.bbz.sanguo.net.protobuf.MsgProtocol.Request;
import static com.bbz.sanguo.net.protobuf.MsgProtocol.Response.Builder;

/**
 * user         LIUKUN
 * time         2014-6-6 19:05
 */
public interface IHandlerWithUser extends IHandler{
    public abstract void run( Request request, Builder responseBuilder, Object user );
}
