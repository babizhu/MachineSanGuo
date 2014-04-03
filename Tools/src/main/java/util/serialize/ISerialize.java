package util.serialize;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 下午4:21
 */
public interface ISerialize{

    /**
     * 序列化
     *
     * @param v 要序列化的对象
     * @return 二进制数组
     */
    <T> byte[] encode( T v );

    /**
     * 反序列化
     *
     * @param bytes 二进制数组
     * @param <T>   对象类型
     * @return 对象
     */
    <T> T decode( byte[] bytes, Class<T> clazz );


}
