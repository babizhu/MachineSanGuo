package experiment.netty.protowithgame.server.handler;

import com.bbz.tool.common.StrUtil;
import com.google.common.collect.Maps;

import java.util.Map;

import static experiment.protocolgen.MsgProtocol.MSG;

/**
 * user         LIUKUN
 * time         2014-5-29 11:37
 * <p/>
 * 通过protobuf中定义的MSG,来获取相应的handler，这里就必须有个规则：<br/>
 * MSG里面的消息定义，然后加上Handler就是相应的类，举例如下<br/>
 * MSG:Login====>LoginHandler.java
 * <p/>
 * MSG里面的首字母推荐大写，不过不是硬性规定
 */

public enum HandlerManager{
    INSTANCE;

    /**
     * handler包的字符串，根据实际情况来调整，后面的"."不要丢了
     */
    public static final String HANDLER_PACKAGE = "experiment.netty.protowithgame.server.handler.";

    private Map<MSG, AbstractHandler> map = Maps.newHashMap();

    HandlerManager(){
        for( MSG msg : MSG.values() ) {
            map.put( msg, buildHandler( msg ) );
        }

        System.out.println( "消息——句柄对应信息：" );
        System.out.println( map );
    }

    public static void main( String[] args ){
        HandlerManager.INSTANCE.buildHandler( MSG.Login );
    }

    /**
     * 通过MSG，利用反射生成相应的处理句柄的实例
     *
     * @param msg   通信包标示符，来自proto文件定义
     * @return 相应的的handler类实例
     */
    private AbstractHandler buildHandler( MSG msg ){
        AbstractHandler handler = null;
        String className = String.format( "%s%sHandler", HANDLER_PACKAGE, StrUtil.firstCharacterToUpper( msg.toString() ) );

        try {
            handler = (AbstractHandler) Class.forName( className ).newInstance();
        } catch( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
            e.printStackTrace();
        }
        return handler;
    }

    public AbstractHandler getHandler( MSG msg ){
        return map.get( msg );
    }
}
