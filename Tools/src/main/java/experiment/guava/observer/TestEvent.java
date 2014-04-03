package experiment.guava.observer;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-11
 * Time: 下午3:19
 */
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:" + message);
    }

    public int getMessage() {
        return message;
    }
}
