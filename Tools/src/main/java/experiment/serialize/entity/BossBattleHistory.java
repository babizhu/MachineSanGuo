package experiment.serialize.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import experiment.lombok.Person;
import experiment.serialize.Student;
import lombok.Data;
import lombok.ToString;
import org.msgpack.annotation.Message;
import util.nosql.NosqlUtil;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Message
@ToString
@Data
public class BossBattleHistory{
    private static final String KEY = "BossBattleActivity";

    Map<Integer, SimpleChallenger> map;
    private RankInfoWithKiller history;
    private final int bossHpMax;
    private Student student;
    private Person person;
    private Object obj;


    BossBattleHistory( int bossHpMax ){
        map = Maps.newHashMap();
        for( int i = 0; i < 10; i++ ) {
            SimpleChallenger sc = new SimpleChallenger( "liukun", "刘老爷", i * 10, i * 100, i );
            map.put( i, sc );
        }
        List<IChallenger> list = Lists.newArrayList();
        history = new RankInfoWithKiller();
        history.setData();
        this.bossHpMax = bossHpMax;
        student = new Student( "aabb", bossHpMax );
        person = new Person();
        person.setName( "aabb" );
        obj = null;

    }

    public BossBattleHistory(){
        bossHpMax = 99999;
    }

    /**
     * 先保存到档期表，完成之后再换
     */
    void saveHistory( List<IChallenger> topTen, IChallenger killer ){


    }

    /**
     * 如果数据库不存在，则返回空
     */
    void loadHistory(){

    }

    public RankInfoWithKiller get(){
        return history;
    }

    public static void main( String[] args ){

        String key = "ab1";
        long begin = System.nanoTime();
        BossBattleHistory bh1 = null;
        for( int i = 0; i < 10000; i++ ) {
            BossBattleHistory bh = new BossBattleHistory( i );

            NosqlUtil.INSTANCE.set( key, bh );

            bh1 = NosqlUtil.INSTANCE.get( key, BossBattleHistory.class );
            if( bh1 == null ) {
                System.out.println( bh1 );
                return;
            }
        }

        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
        System.out.println( bh1 );
        System.out.println( bh1.getMap().size() );
        System.out.println( bh1.getMap().get( 2 ) );

    }

    /**
     * @return bossHpMax
     */
    public int getBossHpMax(){
        return bossHpMax;
    }


}
