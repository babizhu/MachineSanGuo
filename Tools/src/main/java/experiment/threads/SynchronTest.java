package experiment.threads;

/**
 * Created by Administrator on 14-2-11.
 *
 *
 * 测试Synchronized关键字的用法
 *
 * Synchronized关键字一共有三种用法：
 * 1、在类的某个成员变量上使用：
 * Integer lockObject = new Integer(4);
 * synchronized( lockObject ) {}
 *
 * 2、在方法签名上使用
 * synchronized void read(){}
 *
 * 3、在this上使用
 * synchronized( this ) {}
 *
 * test1:
 * 一个类(Account)有两个方法，分别用方法2和方法3修饰，然后用两个不同的线程同时访问，看看情况
 * 结论：互斥执行，也就是说同一个类的方法2和方法3管理的是同一把锁
 *
 * <p/>
 * test2:
 * 两个线程同时测试synchronized( lockObject )的情况
 * 结论：互斥执行
 * <p/>
 * test3:
 * 两个线程同时测试synchronized( lockObject )和synchronized( this )的情况，
 * 结论：互不干涉，交叉执行
 */
class Account{
    Object lockObject = new Object();

    void lockObj(){
        synchronized( lockObject ) {
            System.out.println(Thread.currentThread().getName() + " begin testLock lockObject");
            try {
                Thread.sleep(1500);
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end testLock lockObject");
        }
    }

    synchronized void read(){
        System.out.println("begin read ");
        try {
            Thread.sleep(1000);
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println("end read ");
    }

    void write(){
        System.out.println("WRITE BEFORE synchronized");
        synchronized( this ) {
            System.out.println("begin write ");
            try {
                Thread.sleep(1000);
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
            System.out.println("end write ");
        }
    }

    void check(){
        System.out.println("begin check ");
        try {
            Thread.sleep(1000);
        } catch( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println("end check ");
    }
}

public class SynchronTest{

    Account account = new Account();

    private class WorkRead implements Runnable{
        @Override
        public void run(){
            account.read();
        }
    }

    private class WorkWrite implements Runnable{
        @Override
        public void run(){
            account.write();
        }
    }

    private class WorkCheck implements Runnable{
        @Override
        public void run(){
            account.check();
        }
    }

    private class WorkLockObj implements Runnable{
        @Override
        public void run(){
            account.lockObj();
        }
    }

    void test1(){
        new Thread(new WorkRead()).start();
        new Thread(new WorkWrite()).start();
//        new Thread( new WorkCheck() ).start();
    }

    void test2(){
        new Thread(new WorkLockObj()).start();
        new Thread(new WorkLockObj()).start();
    }

    void test3(){
        new Thread(new WorkRead()).start();
        new Thread(new WorkLockObj()).start();
    }

    public static void main(String[] args){
        new SynchronTest().test2();
    }
}
