package util.nosql;

/**
 * user         LIUKUN
 * time         14-3-24 下午6:05
 * <p/>
 * nosql工具类接口
 */
public interface INosql{
    void set( String k, Object v );

    /**
     * 从数据库中获取数据，如果不存在，则返回null
     *
     * @param k     key
     * @param clazz class类型
     * @return 结果
     */
    <T> T get( String k, Class<T> clazz );
}
