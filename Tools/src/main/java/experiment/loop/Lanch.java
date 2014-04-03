package experiment.loop;

/**
 * 死循环测试
 * User: Administrator
 * Date: 14-1-6
 * Time: 上午11:40
 */
public class Lanch{
    public static void main(String[] args){
        LoopA la = new LoopA();
        LoopB lb = new LoopB();

        la.lb = lb;
        lb.la = la;

        la.get();
    }
}
