package experiment.dynamicproxy;

/**
 * user         LIUKUN
 * time         2014-8-19 17:13
 */

public class RealSubject implements ISubject{
    @Override
    public void request(){
        System.out.println( "From real subject." );
    }
}
