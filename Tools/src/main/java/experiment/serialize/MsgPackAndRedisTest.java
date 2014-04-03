package experiment.serialize;

import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.serialize.Serialize;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 14-3-20.
 */
public class MsgPackAndRedisTest{

    private static final int COUNT = 100;
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

    static void testObj(){
        Jedis jedis = pool.getResource();
        String key = "student";
        Student student = new Student( "likun", 30 );
        byte[] bytes = Serialize.INSTANCE.encode( student );
//        jedis.set
        jedis.set( key.getBytes(), bytes );


        byte[] content = jedis.get( key.getBytes() );
        Student student1 = Serialize.INSTANCE.decode( content, Student.class );
        System.out.println( student1 );
//        System.out.println( student );


        // 释放对象池
        pool.returnResource( jedis );
    }

    static void testList(){
        Jedis jedis = pool.getResource();
        String key = "AnimationList";

        List<Animation> list = buildList();
        byte[] bytes = Serialize.INSTANCE.encode( list );
//        jedis.set
        jedis.set( key.getBytes(), bytes );


        byte[] content = jedis.get( key.getBytes() );
        Animation student1 = Serialize.INSTANCE.decode( content, Animation.class );
        System.out.println( student1 );
//        System.out.println( student );


        // 释放对象池
        pool.returnResource( jedis );
    }


    static List<Animation> buildList(){
        List<Animation> list = Lists.newArrayList();
        for( int i = 0; i < COUNT; i++ ) {
            list.add( new Animation( "test" + i, i, new Student() ) );
        }
        return list;
    }

    public static void main( String[] args ){
        testObj();

    }
}
