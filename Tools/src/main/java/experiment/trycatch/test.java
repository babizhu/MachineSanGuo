package experiment.trycatch;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
public class test {

    static int buildCatch() {
        try {
            int i = 100;
            i = 10 / 0;
            System.out.println("try");
            return 6;
        } catch (Exception e) {
            System.out.println("catch");
//            System.exit( 101 );
            return 8;

        } finally {
            System.out.println("finally");
            //return 9;
        }
        //return 10;
    }

    public static void main(String[] args) {
        System.out.println(test.buildCatch());
    }
}
