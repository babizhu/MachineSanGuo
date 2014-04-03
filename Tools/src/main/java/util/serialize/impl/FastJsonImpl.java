package util.serialize.impl;


import com.alibaba.fastjson.JSON;
import util.serialize.ISerialize;


/**
 * 这是一个采用FastJson的实现
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 下午4:29
 */
public class FastJsonImpl implements ISerialize{

    @Override
    public <T> byte[] encode( T v ){
        return JSON.toJSONBytes( (Object) v );
    }

    @Override
    public <T> T decode( byte[] bytes, Class<T> clazz ){
        return JSON.parseObject( bytes, clazz );
    }
}
