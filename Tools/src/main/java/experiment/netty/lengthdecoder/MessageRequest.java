package experiment.netty.lengthdecoder;

import io.netty.buffer.ByteBuf;

/**
 * user         LIUKUN
 * time         2014-5-22 17:38
 * 客户端发来的消息，解码后的类
 */

public class MessageRequest{
    /**
     * 操作码，登陆？注销？副本？
     */
    private short opCode;

    /**
     * 序列号，用于匹配一个一个response和request
     */
    private int serialNo;
    /**
     * 包长度，不包括包头的所有信息，仅包括数据内容
     */
    private short len;

    /**
     * 参数
     */
    private ByteBuf data;
}
