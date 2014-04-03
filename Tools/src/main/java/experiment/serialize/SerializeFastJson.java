package experiment.serialize;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 上午12:17
 */


public class SerializeFastJson {

    public static void main(String[] args) {

        List<NpcTemplet> list = new ArrayList<NpcTemplet>();
        for (int i = 0; i < Student.LIST_COUNT; i++) {

            NpcTemplet t = new NpcTemplet();
            list.add(t);
        }

//        test3(list);
//        test2 ();;
        test1();


    }


    private static void test3(List<NpcTemplet> list) {
        int maxLen = 0;
        long begin = System.nanoTime();

        String jsonString = JSON.toJSONString(list);
        List<NpcTemplet> l = JSON.parseArray(jsonString, NpcTemplet.class);
        System.out.println(l.size());
        System.out.println((System.nanoTime() - begin) / 1000000000f);
        System.out.println("maxLen is " + jsonString.length());

        //System.out.println ( jsonString );
    }


    private static void test1() {
        Student student = new Student();
        student.name = "msgpack";
        student.age = 100000000;

        int maxLen = 0;
        long begin = System.nanoTime();
        String jsonString = null;
        for (int i = 0; i < Student.COUNT; i++) {
            student.age = i;
            jsonString = JSON.toJSONString(student);
            Student s = JSON.parseObject(jsonString, Student.class);
            maxLen = Math.max(maxLen, jsonString.length());
//            System.out.println (jsonString);

        }
        System.out.println("maxLen is " + maxLen);

        System.out.println((System.nanoTime() - begin) / 1000000000f);
        System.out.println(jsonString);
    }

    private static void test2() {
        Student student = new Student();
        student.name = "msgpack";
        student.age = 100000000;

        int maxLen = 0;
        long begin = System.nanoTime();
        String jsonString = null;
        for (int i = 0; i < Student.COUNT; i++) {
            student.age = i;
            byte[] bytes = JSON.toJSONBytes(student);
            Student s = JSON.parseObject(jsonString, Student.class);
            maxLen = Math.max(maxLen, bytes.length);
//            System.out.println (jsonString);

        }
        System.out.println("maxLen is " + maxLen);

        System.out.println((System.nanoTime() - begin) / 1000000000f);
        System.out.println(jsonString);
    }
}
