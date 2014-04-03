package experiment.serialize;

import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 上午12:49
 */


public class SerializeJsonSmart{


    private static void test1(Student student){
        int maxLen = 0;
        long begin = System.nanoTime ();
        String jsonString = null;
        for( int i = 0; i < Student.COUNT; i++ ) {
            student.age = i;
            jsonString = JSONValue.toJSONString (student, JSONStyle.MAX_COMPRESS);
            maxLen = Math.max (maxLen, jsonString.length());
//            System.out.println (jsonString);

        }
        System.out.println ( "maxLen is " + maxLen );

        System.out.println ((System.nanoTime () - begin) / 1000000000f);
        System.out.println ( jsonString );
    }

    public static void main(String[] args){
        Student student = new Student ();
        student.age = 4;
        student.name= "liukun";
        test1 (student);
    }
}
