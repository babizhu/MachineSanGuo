package experiment.guava;

import com.google.common.base.Enums;
import com.google.common.base.Function;

/**
 * Created by Administrator on 14-1-27.
 */
public class EnumsTest{

    enum Type{DAY, MONTH, DEFAULT_DATE, YEAR}

    public static void main(String[] args){

        System.out.println(Enums.getField(Type.DAY));

//        Function<String, Type> function = Enums.valueOfFunction(Type.class);
        Function<String, Type> function1 = Enums.stringConverter(Type.class);
//        function.apply( null );
        System.out.println(function1.apply(null));
        System.out.println(function1.apply("DAY"));
//        Type t = Enums.valueOfFunction(Type.class).apply("MONTH");
//        System.out.println(t);

        Type t = Enums.getIfPresent(Type.class, "YEAR").or(Type.DEFAULT_DATE);
        System.out.println(t);
    }
}
