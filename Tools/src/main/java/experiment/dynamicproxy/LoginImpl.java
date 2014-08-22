package experiment.dynamicproxy;

/**
 * user         LIUKUN
 * time         2014-8-19 17:48
 */

public class LoginImpl implements ILogin{
    @Override
    public boolean login( String name ){
        System.out.println( "LoginImpl.login" );
        if( name.equals( "lk" ) ) {
            return true;
        }
        return false;
    }
}
