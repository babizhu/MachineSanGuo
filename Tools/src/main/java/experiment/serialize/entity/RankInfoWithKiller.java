package experiment.serialize.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.msgpack.annotation.Message;
import util.serialize.Serialize;

import java.util.List;

/**
 * 不能采用接口
 */
@Message
@Data
@ToString
public class RankInfoWithKiller{
    private SimpleChallenger killer;
    private List<SimpleChallenger> list = Lists.newArrayList();
    private int bossHpMax;

    public void setData(){
        for( int i = 0; i < 10; i++ ) {
            SimpleChallenger sc = new SimpleChallenger( "liukun", "刘老爷", i * 10, i * 100, i );
            list.add( sc );
        }

        killer = new SimpleChallenger( "killer", "杀手", 50, 500, 5000 );

        this.bossHpMax = 1000001;
    }

    public RankInfoWithKiller(){
    }


    public static void main( String[] args ){
        RankInfoWithKiller rankInfoWithKiller = new RankInfoWithKiller();
        rankInfoWithKiller.setData();
        byte[] encode = Serialize.INSTANCE.encode( rankInfoWithKiller );

        RankInfoWithKiller r1 = Serialize.INSTANCE.decode( encode, RankInfoWithKiller.class );
        System.out.println( r1 );

    }
}
