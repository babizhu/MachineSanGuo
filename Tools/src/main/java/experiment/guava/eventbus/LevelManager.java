package experiment.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by Administrator on 14-1-16.
 */
public class LevelManager {

    EventBus chanle = new EventBus();
    int level;

    public LevelManager() {

        this.chanle = chanle;
    }

    public void levelUp() {
        level++;
//        chanle.post( level+"" );
    }

    @Subscribe
    public void levelUp(String s) {
        System.out.println("LevelManager" + s);
    }

    public static void main(String[] args) {
//        EventBus chanel = new EventBus();
////        LevelManager levelManager = new LevelManager( chanel );
//
//        Formation formation = new Formation( chanel );
//
////        chanel
//        chanel.register(levelManager);
//        chanel.register( formation );
//
//
//        //chanel.post("aaaaaaaa");
//        levelManager.levelUp();
    }

}
