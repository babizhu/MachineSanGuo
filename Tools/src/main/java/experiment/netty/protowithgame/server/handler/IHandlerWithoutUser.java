package experiment.netty.protowithgame.server.handler;

import io.netty.channel.ChannelHandlerContext;

import static experiment.protocolgen.MsgProtocol.Request;
import static experiment.protocolgen.MsgProtocol.Response.Builder;

/**
 * user         LIUKUN
 * time         2014-6-6 11:49
 * 所有无需登录就可以执行的处理器的接口
 */

public interface IHandlerWithoutUser extends IHandler{

    public abstract void run( Request request, Builder responseBuilder, ChannelHandlerContext ctx );
}
