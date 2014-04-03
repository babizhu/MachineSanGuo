package util.nosql;

/**
 * user         LIUKUN
 * time         14-3-24 下午6:05
 * <p/>
 * Nosql工具类，提供外层使用，可在这里灵活设置底层的nosql实现
 */
public enum NosqlUtil{
    INSTANCE;

    private INosql nosql = new RedisImpl();

    public void set( String k, Object v ){
        nosql.set( k, v );
    }

    public <T> T get( String k, Class<T> clazz ){
        return nosql.get( k, clazz );
    }

    public static void main( String[] args ){

    }

}

