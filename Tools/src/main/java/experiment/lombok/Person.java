package experiment.lombok;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.msgpack.annotation.Message;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 上午10:34
 */
public
@ToString(exclude = "id")
@Data
@Message
class Person{
    String name;
    int age;
    int id;

    public void NonNullExample( @NonNull String person ){
        System.out.println( "Hello" );
    }

    public static void main( String[] args ){
        Person p = new Person();
        p.setAge( 20 );
        p.getName();

        System.out.println( p );
        p.NonNullExample( null );

        ConstructorExample<String> constructorExample = new ConstructorExample<String>( 10, 20, "d" );
        //ConstructorExample<String> constructorExample1 = new ConstructorExample<String>("d");

    }

}


