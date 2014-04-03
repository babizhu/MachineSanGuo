package experiment.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Administrator on 14-1-16.
 * <p/>
 * 阵容可开启的位置数量由user的等级决定
 */

public class Formation {
    //private final EventBus channel;
    /**
     * 空格子的数目
     */
    private int count;
    private EventBus chanel;

    public Formation(EventBus chanel) {
        this.chanel = chanel;
    }

    @Subscribe
    public void levelUp(String s) {

        System.out.println("Formation" + s);
    }

    void test1() {
        chanel.post("xxxxxxxxxxx");
    }

}
