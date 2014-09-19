package com.bbz.sanguo.net.handler;

import experiment.protocolgen.MsgProtocol;

/**
 * user         LIUKUN
 * time         2014-5-28 19:54
 */

public abstract class AbstractHandler{
    public abstract void run( MsgProtocol.Request request, MsgProtocol.Response.Builder responseBuilder );
}
