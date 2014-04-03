package experiment.fsl;

import com.google.common.collect.Lists;
import experiment.serialize.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-19
 * Time: 下午5:54
 */
public class LinkedListTest {

    LinkedList<Student> list = Lists.newLinkedList();
    List<Student> cl = Lists.newLinkedList();

    public LinkedListTest() {
        for (int i = 0; i < 10; i++) {
            Student s = new Student("liukun" + i, i);
            list.add(s);
//            System.out.println( s.getName());
//            ListIterator<Student> current1 = cl.
        }
    }

    boolean run() {
        return true;
    }

    void test() {
        int age = 5;
        ListIterator<Student> current = findCurrentFromAge(age);
//        System.out.println(current.nextIndex());
//        System.out.println( current.previous().getName() );

        int currentIndex = 1; //从数据库获取的节点
//        current = list.listIterator( 1 );   //从数据库获取的节点
        //此时客户端调用不同的run(path,node)例如battleRun,prizeRun(path,node)等等

        //根据currentIndex检测有效性
        System.out.println(list.get(currentIndex).getName());

//        System.out.println( current.next().getName());   //得到从数据库获取的节点的内容，即，玩家已经完成此节点的内容，站在此节点上，等待next命令向下一个节点进发
//        System.out.println( current.next().getName());//获取到下一节点内容
//        current.previous();//如果闯关失败，回退此节点
//        System.out.println( current.next().getName());
//
////        System.out.println( current.previous().getName() );
//        System.out.println( current.nextIndex() );


//        System.out.println( current.next().getName() );
    }

    public static void main(String[] args) {
        new LinkedListTest().test();
    }

    ListIterator<Student> findCurrentFromAge(int age) {

//        Iterator<Student> iter = list.iterator();
        ListIterator<Student> iter = list.listIterator();
        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getAge() == age) {
                return iter;
            }
        }
        return null;
    }
}
