package experiment.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * user         LIUKUN
 * time         2014-8-19 16:56
 */

public class InfoManagerFactory{
    private static InfoManager manger = new InfoManager();

    /**
     * 创建原始的InfoManager
     *
     * @return
     */
    public static InfoManager getInstance(){
        return manger;
    }

    public static InfoManager getAuthInstance( AuthProxy auth ){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass( InfoManager.class );
        enhancer.setCallback( auth );
        return (InfoManager) enhancer.create();
    }
}
