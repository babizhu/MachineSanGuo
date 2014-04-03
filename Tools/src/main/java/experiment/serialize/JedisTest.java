package experiment.serialize;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-20
 * Time: 下午12:14
 */
public class JedisTest{

    private static JedisPool pool = null;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if( bundle == null ) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!");
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
        config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

        pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
    }

    static void test1(){

        Jedis jedis = pool.getResource();
        String keys = "name";

// 删数据
        //jedis.del(keys);
//存数据
        jedis.set(keys, "snowolf");
        jedis.append(keys, "liukun");
// 取数据
        String value = jedis.get(keys);

        System.out.println(value);

        jedis.mset("name", "minxr", "jarorwar", "aaa");
        System.out.println(jedis.mget("name", "jarorwar"));

        System.out.println(jedis.get("jarorwar"));
        System.out.println(jedis.flushDB());
        System.out.println(jedis.get("jarorwar"));

        //jedis.set
        //jedis.

// 释放对象池
        pool.returnResource(jedis);
//        RedisUtil
    }

    public static void testList(){
        System.out.println("==List==");
        Jedis jedis = pool.getResource();
        try {
            // 开始前，先移除所有的内容
            jedis.del("messages");
            jedis.rpush("messages", "Hello how are you?");
            jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");
            jedis.rpush("messages", "I should look into this NOSQL thing ASAP");


            // 再取出所有数据jedis.lrange是按范围取出，
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            List<String> values = jedis.lrange("messages", 0, -1);
            System.out.println(values.getClass());
            CopyOnWriteArrayList<String> cl = new CopyOnWriteArrayList<String>(values);
            System.out.println(values);

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            //pool.returnResource(jedis);
        }

        // 清空数据
        System.out.println(jedis.flushDB());
        // 添加数据
        jedis.lpush("lists", "vector");
        jedis.lpush("lists", "ArrayList");
        jedis.lpush("lists", "LinkedList");
        // 数组长度
        System.out.println("jedis.llen is " + jedis.llen("lists"));
        // 排序
        //System.out.println(jedis.sort("lists"));
        // 字串
        System.out.println(jedis.lrange("lists", 0, 3));
        // 修改列表中单个值
        jedis.lset("lists", 0, "hello list!");
        // 获取列表指定下标的值
        System.out.println(jedis.lindex("lists", 1));
        // 删除列表指定下标的值
        System.out.println(jedis.lrem("lists", 1, "vector"));
        // 删除区间以外的数据
        System.out.println(jedis.ltrim("lists", 0, 1));
        // 列表出栈
        System.out.println(jedis.lpop("lists"));
        // 整个列表值
        System.out.println(jedis.lrange("lists", 0, -1));

        Set<String> setValues = jedis.smembers("myset");

        System.out.println(setValues.getClass());
    }

    private static void testKey(){
        Jedis jedis = pool.getResource();
        System.out.println("=============key==========================");
        // 清空数据
        System.out.println(jedis.flushDB());
        System.out.println(jedis.echo("foo"));
        // 判断key否存在
        System.out.println(jedis.exists("foo"));
        jedis.set("key", "values");
        System.out.println(jedis.exists("key"));

        jedis.setnx("name", "liukun");
        System.out.println(jedis.get("name"));

        jedis.setnx("name", "liukun11");
        System.out.println(jedis.get("name"));


        // 获取并更改数据
        jedis.set("foo", "foo update");
        jedis.set("foo1", "foo update");
        System.out.println(jedis.getSet("foo", "foo modify"));

        System.out.println(jedis.getrange("foo", 1, 3));

        System.out.println(jedis.mset("mset1", "mvalue1", "mset2", "mvalue2",
                "mset3", "mvalue3", "mset4", "mvalue4"));
        System.out.println(jedis.mget("mset1", "mset2", "mset3", "mset4"));
        System.out.println(jedis.del(new String[]{"foo", "foo1", "foo3"}));


    }


    public static void main(String[] args){
        testList();
    }
}
