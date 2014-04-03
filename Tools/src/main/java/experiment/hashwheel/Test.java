package experiment.hashwheel;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 14-1-28.
 */

class Task1 implements ITimerTask{

    @Override
    public void run(ITimeout timeout) throws Exception{
        System.out.println(new DateTime().toLocalTime());

    }
}

public class Test{
    public static void main(String[] args){

//        for( int i = 100; i < 202; i++ ) {
//            System.out.println(i + ":" + ((i + 99) / 100) + ":" + i / 100 + ":" + (int) Math.ceil(i / 100f));
//        }
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(-Long.MAX_VALUE);
//        System.out.println(-Long.MIN_VALUE);
//        System.out.println(Long.MIN_VALUE);
//        System.out.println("-Long.MIN_VALUE == Long.MIN_VALUE ? " + (-Long.MIN_VALUE == Long.MIN_VALUE));
//        System.out.println("-Long.MAX_VALUE == Long.MAX_VALUE ? " + (-Long.MAX_VALUE == Long.MAX_VALUE));
//        System.out.println("-Byte.MAX_VALUE == Byte.MAX_VALUE ? " + (-Byte.MAX_VALUE == Byte.MAX_VALUE));
//        System.out.println("-Byte.MIN_VALUE == Byte.MIN_VALUE ? " + (-Byte.MIN_VALUE == Byte.MIN_VALUE));
//        System.out.println("-Byte.MIN_VALUE =" + -Byte.MIN_VALUE + " Byte.MIN_VALUE = " + Byte.MIN_VALUE);
//        System.out.println("-Long.MIN_VALUE =" + -Long.MIN_VALUE + " Long.MIN_VALUE = " + Long.MIN_VALUE);
//
//        System.out.println("System.nanoTime() = " + System.nanoTime());
//
//        long l = Long.MAX_VALUE - Long.MIN_VALUE;
//        System.out.println("l " + l);
//
//        byte b = (byte) -Byte.MIN_VALUE;
//        System.out.println(b);
//
//        System.out.println("------------------------");
//        System.out.println(-Long.MAX_VALUE + 1);
//        System.out.println(Long.MIN_VALUE - 1);
//        System.out.println(Long.MAX_VALUE + 1);
//        long tickDuration = TimeUnit.SECONDS.toNanos(2);
//
//
//        System.out.println(tickDuration);
//        System.out.println(new LocalTime());

        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        hashedWheelTimer.newTimeout(new Task1(), 1, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(new Task1(), 2, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(new Task1(), 5, TimeUnit.SECONDS);


    }
}
