package experiment.counter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-2
 * Time: 下午12:00
 * 计数器类，考虑12点清空的问题
 */
public interface ICounter {

//    int         lastTime;

    /**
     * 获取当前值
     *
     * @return
     */
    int count();

}
