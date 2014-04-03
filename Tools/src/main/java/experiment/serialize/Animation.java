package experiment.serialize;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.msgpack.annotation.Message;

/**
 * Created by Administrator on 14-3-20.
 */

@Message
@ToString
@AllArgsConstructor
public class Animation{

    private String name;
    private int age;

    public Student getStudent(){
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    private Student student;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }


    public Animation(){
    }

    public static void main(String[] args){
    }

}
