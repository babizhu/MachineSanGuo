package experiment.serialize;

import com.google.common.collect.Lists;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;
import org.msgpack.packer.Packer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-17
 * Time: 上午12:07
 */


@SuppressWarnings("UnusedDeclaration")
public class SerializeMsgPack{
    public static void main(String[] args) throws IOException{


        List<NpcTemplet> list = new ArrayList<NpcTemplet>();
        for( int i = 0; i < Student.LIST_COUNT; i++ ) {

            NpcTemplet t = new NpcTemplet();
            list.add(t);
        }

//        System.out.println( list);
//        test3 (list);
//        test1();
        test4();

        // Serialize
    }

    private static void test1() throws IOException{
        Student student = new Student();
        student.name = "msgpack";
        student.age = 100000000;

        MessagePack msgpack = new MessagePack();
        long begin = System.nanoTime();
        int maxLen = 0;
        for( int i = 0; i < Student.COUNT; i++ ) {
            student.age = i;
            byte[] bytes = msgpack.write(student);
            Student s1 = msgpack.read(bytes, Student.class);
            maxLen = Math.max(maxLen, bytes.length);
        }
        System.out.println("maxLen is " + maxLen);

        System.out.println((System.nanoTime() - begin) / 1000000000f);
    }

    private static void test2() throws IOException{
        Student student = new Student();
        student.name = "msgpack";
        student.age = 100000000;

        MessagePack msgpack = new MessagePack();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Packer packer = msgpack.createPacker(out);


        long begin = System.nanoTime();
        int maxLen = 0;
        for( int i = 0; i < Student.COUNT; i++ ) {
            student.age = i;
            packer.write(student);
            //byte[] bytes = msgpack.write (student);
            //msgpack.read (bytes, Student.class);
            //maxLen = Math.max (maxLen, bytes.length);
            //System.out.println ( bytes.length );

        }
        System.out.println("maxLen is " + maxLen);

        System.out.println((System.nanoTime() - begin) / 1000000000f);
    }

    @Message
    static class ListClass{
        public List<Animation> list = Lists.newArrayList();
        public int age;


        ListClass(){
        }
    }

    private static void test4() throws IOException{
        ListClass lc = new ListClass();
        lc.age = 90;
        lc.list.add(new Animation("lk", 1, new Student("s1", 100)));
        lc.list.add(new Animation("lkkk", 111, new Student("s01", 100)));
        MessagePack msMessagePack = new MessagePack();
        byte[] write = msMessagePack.write(lc);

        ListClass lc1 = msMessagePack.read(write, ListClass.class);

        System.out.println(lc1.list.size());
        System.out.println(lc1.list);
        System.out.println(lc1.age);
        System.out.println(lc1.list.get(1).getStudent().getName());


    }

    private static void test3(List<NpcTemplet> list) throws IOException{
        int maxLen = 0;
        MessagePack msgpack = new MessagePack();

        long begin = System.nanoTime();
        byte[] bytes = msgpack.write(list);
        System.out.println((System.nanoTime() - begin) / 1000000000f);
        System.out.println("maxLen is " + bytes.length);


//        List<NpcTemplet> dst1 = (List<NpcTemplet>) msgpack.read(bytes );
//
//        for( NpcTemplet npcTemplet : dst1 ) {
//
//            System.out.println( npcTemplet );
//        }
//        System.out.println( "size: " + dst1.size() );

//        List<NpcTemplet> dst = Lists.newArrayList();
//        List<NpcTemplet> read = msgpack.read(bytes,  );
//        System.out.println( read );

//        msgpack.read()
    }
}
