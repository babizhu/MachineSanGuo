package util.nosql;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.serialize.Serialize;

import java.util.ResourceBundle;

/**
 * user         LIUKUN
 * time         14-3-24 下午6:05
 * <p/>
 * redis的nosql实现
 */
class RedisImpl implements INosql{

    private static JedisPool pool = null;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle( "redis" );
        if( bundle == null ) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!" );
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive( Integer.valueOf( bundle.getString( "redis.pool.maxActive" ) ) );
        config.setMaxIdle( Integer.valueOf( bundle.getString( "redis.pool.maxIdle" ) ) );
        config.setMaxWait( Long.valueOf( bundle.getString( "redis.pool.maxWait" ) ) );
        config.setTestOnBorrow( Boolean.valueOf( bundle.getString( "redis.pool.testOnBorrow" ) ) );
        config.setTestOnReturn( Boolean.valueOf( bundle.getString( "redis.pool.testOnReturn" ) ) );

        pool = new JedisPool( config, bundle.getString( "redis.ip" ), Integer.valueOf( bundle.getString( "redis.port" ) ) );
    }


    @Override
    public void set( String k, Object v ){
        Jedis jedis = pool.getResource();
        byte[] data = Serialize.INSTANCE.encode( v );
        jedis.set( k.getBytes(), data );
        pool.returnResource( jedis );
    }


    @Override
    public <T> T get( String k, Class<T> clazz ){
        Jedis jedis = pool.getResource();
        byte[] data = jedis.get( k.getBytes() );

        if( data == null ) {
            return null;
        }
        T obj = Serialize.INSTANCE.decode( data, clazz );

        pool.returnResource( jedis );
        return obj;
    }
}
