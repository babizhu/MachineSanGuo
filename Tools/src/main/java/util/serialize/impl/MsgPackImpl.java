package util.serialize.impl;


import org.msgpack.MessagePack;
import util.serialize.ISerialize;

import java.io.IOException;

/**
 * 这是一个采用MsgPack的实现
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 下午4:29
 */
public class MsgPackImpl implements ISerialize{

    @Override
    public <T> byte[] encode( T v ){
        MessagePack msgpack = new MessagePack();
        try {
            return msgpack.write( v );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T decode( byte[] bytes, Class<T> clazz ){
        MessagePack msgpack = new MessagePack();
        try {
            return msgpack.read( bytes, clazz );
        } catch( IOException e ) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
