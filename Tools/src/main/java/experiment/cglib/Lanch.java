package experiment.cglib;

/**
 * user         LIUKUN
 * time         2014-8-19 16:57
 * 利用CGLIB创建的自动代理类，相对于jdk自带的动态代理有两个好处
 * 1、无需接口
 * 2、性能较高
 * * http://wsmajunfeng.iteye.com/blog/1790143
 *
 * @see experiment.dynamicproxy
 */

public class Lanch{

    public static void main( String[] args ){
        Lanch c = new Lanch();
        c.anyonecanManager();
        c.haveAuthManager();
        c.haveNoAuthManager();
    }

    /**
     * 模拟：登录会员没有权限
     */
    public void haveNoAuthManager(){
        System.out.println( "the loginer's name is not maurice,so have no permits do manager" );
        InfoManager noAuthManager = InfoManagerFactory.getAuthInstance( new AuthProxy( "maurice1" ) );
        doCRUD( noAuthManager );
        separatorLine();
    }

    /**
     * 模拟：没有任何权限要求，任何人都可以操作
     */
    public void anyonecanManager(){
        System.out.println( "any one can do manager" );
        InfoManager manager = InfoManagerFactory.getInstance();
        doCRUD( manager );
        separatorLine();
    }

    public void haveAuthManager(){
        System.out.println( "the loginer's name is maurice,so have permits do manager" );
        InfoManager authManager = InfoManagerFactory.getAuthInstance( new AuthProxy( "maurice" ) );
        doCRUD( authManager );
        separatorLine();
    }


    /**
     * 对Info做增加／更新／删除／查询操作
     *
     * @param manager
     */
    private void doCRUD( InfoManager manager ){
        manager.create();
        manager.update();
        manager.delete();
        manager.query();
    }

    /**
     * 加一个分隔行，用于区分
     */
    private void separatorLine(){
        System.out.println( "################################" );
    }

}