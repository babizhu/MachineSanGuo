package com.bbz.sanguo.net.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-29 12:04
 * 把student类转换为一个字符串
 * <>中的类（student）指的输入类
 */

public class GameCodec3 extends MessageToMessageDecoder<Student>{
    @Override
    protected void decode( ChannelHandlerContext ctx, Student msg, List<Object> out ) throws Exception{
        out.add( msg.toString() );
    }
}
