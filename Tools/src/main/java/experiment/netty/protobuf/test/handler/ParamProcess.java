package experiment.netty.protobuf.test.handler;

/**
 * user         LIUKUN
 * time         2014-5-27 13:38
 */

class Father{

}

class Son1 extends Father{

}

class Son2 extends Father{

}

public class ParamProcess{

    public static void main( String[] args ){
        Son1 son1 = new Son1();
        Father f = son1;
        //new ParamProcess().run( f );

    }

    void run( Son1 son1 ){
        System.out.println( "son1 = [" + son1 + "]" );
    }

    void run( Son2 son2 ){
        System.out.println( "son2 = [" + son2 + "]" );
    }
}
