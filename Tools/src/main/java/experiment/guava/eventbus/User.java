package experiment.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created by Administrator on 14-1-16.
 */
public class User<T> {

    public static int var = 0;

    //    LevelManager lv;


    //    BaseProp
    int level;

    Formation formation;
    EventBus bus;

    public User() {
    }

    public void levelUp() {
        //formation.levelUp(level);
        level++;
//        bus.post(new LevelUpEvent());

    }

    static void m(String s) {
        System.out.println("String" + s);

    }

    static void m(Integer i) {
        System.out.println("integer" + i);
    }

    public static void main(String[] args) {
        User<Integer> gti = new User<Integer>();
        gti.var = 1;
        User<String> gts = new User<String>();
        gts.var = 2;
        System.out.println(gti.var);
        m(2);
        String s = null;
        m(s);


    }

    @Test
    void Test() {

    }

    class GT<T> {
        public void nothing(T x) {
        }
    }

}
