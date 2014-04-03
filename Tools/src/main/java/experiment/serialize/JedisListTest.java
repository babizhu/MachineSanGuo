package experiment.serialize;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * user         LIUKUN
 * time         14-3-25 下午4:56
 */
@Data
@AllArgsConstructor
class DataDto{
    private int age;
    private String name;


}

public class JedisListTest{
    static void test1(){

        List<DataDto> list = buildList();

        String key = "listTest1";
        System.out.println( "==List==" );
        Jedis jedis = JedisUtil.pool.getResource();

        //jedis.lpush( key.getBytes(), Serialize.INSTANCE.encode( list.get( 0 ) ) );
//        jedis.lpushx(  )

        try {
            // 开始前，先移除所有的内容
            jedis.del( "messages" );
            jedis.rpush( "messages", "Hello how are you?" );
            jedis.rpush( "messages", "Fine thanks. I'm having fun with redis." );
            jedis.rpush( "messages", "I should look into this NOSQL thing ASAP" );


            // 再取出所有数据jedis.lrange是按范围取出，
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            List<String> values = jedis.lrange( "messages", 0, -1 );
            System.out.println( values.getClass() );
            CopyOnWriteArrayList<String> cl = new CopyOnWriteArrayList<String>( values );
            System.out.println( values );

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            //pool.returnResource(jedis);
        }

        // 清空数据
        System.out.println( jedis.flushDB() );
        // 添加数据
        jedis.lpush( "lists", "vector" );
        jedis.lpush( "lists", "ArrayList" );
        jedis.lpush( "lists", "LinkedList" );
        // 数组长度
        System.out.println( "jedis.llen is " + jedis.llen( "lists" ) );
        // 排序
        //System.out.println(jedis.sort("lists"));
        // 字串
        System.out.println( jedis.lrange( "lists", 0, 3 ) );
        // 修改列表中单个值
        jedis.lset( "lists", 0, "hello list!" );
        // 获取列表指定下标的值
        System.out.println( jedis.lindex( "lists", 1 ) );
        // 删除列表指定下标的值
        System.out.println( jedis.lrem( "lists", 1, "vector" ) );
        // 删除区间以外的数据
        System.out.println( jedis.ltrim( "lists", 0, 1 ) );
        // 列表出栈
        System.out.println( jedis.lpop( "lists" ) );
        // 整个列表值
        System.out.println( jedis.lrange( "lists", 0, -1 ) );

        Set<String> setValues = jedis.smembers( "myset" );

        System.out.println( setValues.getClass() );
    }

    private static List<DataDto> buildList(){
        List<DataDto> list = Lists.newArrayList();
        for( int i = 0; i < 100; i++ ) {
            DataDto dataDto = new DataDto( i, "lk" + i );
            list.add( dataDto );
        }
        return list;
    }

}
