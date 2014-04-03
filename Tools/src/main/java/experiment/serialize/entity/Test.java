package experiment.serialize.entity;


import util.serialize.Serialize;

/**
 * Created by Administrator on 14-3-21.
 */
public class Test{
    public static void main( String[] args ){
        SimpleChallenger simpleChallenger = new SimpleChallenger( "liukun", "刘老爷", 200, 1000, 1 );

        byte[] encode = Serialize.INSTANCE.encode( simpleChallenger );
        SimpleChallenger simpleChallenger1 = Serialize.INSTANCE.decode( encode, SimpleChallenger.class );
        System.out.println( simpleChallenger1 );
    }
}
