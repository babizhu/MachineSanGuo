package experiment.netty.lingkong;

import experiment.netty.lingkong.client.LingkongClient;

/**
 * user         LIUKUN
 * time         2014-5-23 15:56
 */

public class LinekongPlatform implements IThirdPartyPlatform{

    private LingkongClient client = new LingkongClient();

    public static void main( String[] args ){
        IThirdPartyPlatform platform = new LinekongPlatform();
        platform.login();
    }

    @Override
    public void login(){
        System.out.println( "login" );
    }

}
