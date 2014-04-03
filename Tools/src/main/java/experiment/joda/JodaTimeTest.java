package experiment.joda;

import org.joda.time.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午2:30
 */
public class JodaTimeTest {
    static void create() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);

        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 05);
        System.out.println(dateTime);
    }

    static void add() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println(sdf.format(calendar.getTime()));
        /***********************************新方式****************************************/
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));

    }

    /**
     * 我希望计算上一个月的最后一天。对于这个例子，我并不关心一天中的时间
     */
    static void lastDayOfPreviousMonth() {
        LocalDate now = new LocalDate();
        LocalDate lastDayOfPreviousMonth = now.minusMonths(1).dayOfMonth().withMaximumValue();
        System.out.println(lastDayOfPreviousMonth);
        LocalDate now1 = new LocalDate();

        if (now == now1) {
            System.out.println("同一天");
        }
        if (now.equals(now1)) {
            System.out.println("同一天");
        }

        System.out.println(now.getValue(0));
        System.out.println(now.getYear());
        System.out.println(now.getYearOfCentury());
        System.out.println(now.getEra());
        System.out.println(Arrays.toString(now.getValues()));
        System.out.println(now.toDate().getTime());
        now.plusDays(1);
        System.out.println(now.toDate().getTime());

    }

    static void test1() {
        DateTime start = new DateTime(2013, 12, 2, 15, 33);//开始时间
        DateTime end = new DateTime(2013, 12, 3, 15, 34);//结束时间
        Interval inteval = new Interval(start, end);
        Duration duration = new Duration(start, end);
        Period period = inteval.toPeriod();

        boolean between = inteval.contains(new DateTime(2013, 2, 1, 12, 34));//判断指定时间在这段时间间隔里: True
        long millSeconds = duration.getMillis();//得到两个时间相差的豪秒数:39301260000
        System.out.println(duration.getStandardDays() + "天");
        System.out.println(duration.getStandardHours() + "小时");
        System.out.println(duration.getStandardMinutes() + "分钟");

        long millSeconds1 = period.getMillis();//得到两个时间相差的豪秒数:0
        System.out.println(period.getDays() + "天" + period.getHours() + "小时" + period.getMinutes() + "分钟" + period.getSeconds() + "秒");
    }

    public static void main(String[] args) {
        create();
        add();
        lastDayOfPreviousMonth();
        test1();
    }
}
