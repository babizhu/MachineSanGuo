package util.serialize;


import util.serialize.impl.FastJsonImpl;

/**
 * User: liukun
 * Date: 13-11-17
 * Time: 下午4:50
 */
public enum Serialize{
    INSTANCE;

    public <T> byte[] encode( T v ){
        //ZipUtil.bZip2()压缩
        return serialize.encode( v );
    }

    public <T> T decode( byte[] bytes, Class<T> clazz ){
        return serialize.decode( bytes, clazz );
    }

    private final ISerialize serialize;

    private Serialize(){
//        serialize = new MsgPackImpl();
        serialize = new FastJsonImpl();
    }

    public static void main( String[] args ){


    }
}
