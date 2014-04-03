package experiment.serialize;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * User: Administrator
 * Date: 13-11-20
 * Time: 下午6:15
 */

public class JedisUtil{
    static{
        init();
    }

    public static JedisPool pool;

    private static void init(){
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


}
