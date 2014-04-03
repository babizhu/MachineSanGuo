package experiment.counter;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 14-2-8.
 * 测试windows下面的System.currentTimeMillis()的精确程度,感觉在win7上还是比较准确了
 */
public class ClockAccuracyTest{
    public static void main(String args[]) throws InterruptedException{

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSS");
        int size = 4000000;

        // create an array to hold millisecond times
        // and loop to capture them
        long times[] = new long[size];
        for( int i = 0; i < size; i++ ) {
            times[i] = System.currentTimeMillis();
        }

        // now display the unique times
        long time = times[0];
        long previousTime = times[0];
        long count = 0;
        Set<Long> deltas = new HashSet<Long>();
        long delta = 0;
        long minDelta = Long.MAX_VALUE;
        long maxDelta = Long.MIN_VALUE;
        for( int i = 0; i < size; i++ ) {
            if( times[i] > time ) {
                delta = time - previousTime;
                deltas.add(delta);
                if( delta > 0 && delta < minDelta ) {
                    minDelta = delta;
                } else if( delta > maxDelta ) {
                    maxDelta = delta;
                }

                System.out.print("raw=");
                System.out.print(time);
                System.out.print(" | formatted=");
                System.out.print(new DateTime(time));
                System.out.print(" | frequency=");
                System.out.print(count);
                System.out.print(" | delta=");
                System.out.print(delta);
                System.out.println("ms");

                previousTime = time;
                time = times[i];
                count = 0;
            } else {
                count++;
            }
        }

        System.out.println("");
        System.out.println("Minimum delta : " + minDelta + "ms");
        System.out.println("Maximum delta : " + maxDelta + "ms");


        long l1 = System.currentTimeMillis();
        System.out.println(l1);
        ;
        Thread.sleep(101);
        System.out.println(System.currentTimeMillis() - l1);
        ;
    }
}
