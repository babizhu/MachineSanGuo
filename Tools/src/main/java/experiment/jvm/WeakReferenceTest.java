package experiment.jvm;

import experiment.lombok.Person;

import java.lang.ref.WeakReference;

/**
 * user         LIUKUN
 * time         2014-9-2 15:15
 */

public class WeakReferenceTest{
    public static void main( String[] args ){
        Person p = new Person();
        p.setName( "liukun" );

        WeakReference<Person> weak = new WeakReference<Person>( p );
        //SoftReference<Person> soft = new SoftReference<Person>( p );//加上这一句，只要jvm不发生oom错误，循环永远不会结束

        p = null;
        int i = 0;
        while( weak.get() != null ) {
            System.out.println( String.format( "Get str from object of WeakReference: %s, count: %d", weak.get().getName(), ++i ) );
            if( i % 10 == 0 ) {
                System.gc();
                System.out.println( "System.gc() was invoked!" );
            }
            try {
                Thread.sleep( 500 );
            } catch( InterruptedException e ) {

            }
        }
        System.out.println( "object person was cleared by JVM!" );


    }
}
