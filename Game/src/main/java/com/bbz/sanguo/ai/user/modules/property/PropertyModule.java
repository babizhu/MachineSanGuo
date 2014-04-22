package com.bbz.sanguo.ai.user.modules.property;

import com.bbz.sanguo.ai.user.ModuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user         LIUKUN
 * time         2014-4-8 16:28
 * 管理玩家的属性包括
 * 铜钱
 * 元宝
 * 体力
 *
 */

public class PropertyModule{

    private static Logger logger = LoggerFactory.getLogger( PropertyModule.class );

    private final PropertyDataProvider          db;
    private final UserProperty                  property;
    private final String uname;

    public PropertyModule( ModuleManager manager ){
        this.uname = manager.getUserName();
        db = new PropertyDataProvider( uname );
        property = db.findOne();
        //this.moduleManager = moduleManager;
    }


    /**
     * 玩家涉及到属性的更改操作
     * @param type              奖励类型
     * @param change            改变的数值
     * @param funcName          改变的方法名
     * @return
     * 		>=0	:返回此属性的当前值
     * 		-1	:扣除失败，余值不足
     *
     */
    public int changeValue( UserPropertyType type, int change, String funcName ){
        int result;
        switch( type ) {
            case CASH:
                result = property.addCash( change );
                break;
            case GOLD:
                result = property.addGold( change );
                break;
            default:
                throw new IllegalArgumentException( type + "属性不存在相应函数" );
        }
        if( result == -1 ) {
            logger.debug( uname + "奖励类型：" + type + "欲改变数值：" + change + "，出现负数，调用函数为" + funcName );
        }
        buildLog( type, change, result, funcName );
        db.replace( property );
        return result;
    }
    /**
     * 构造关键数据的日志文件
     *
     * @param at       奖励类型
     * @param change   改动的数值
     * @param current  改动后的当前数值
     * @param funcName 改动函数
     */
    private void buildLog( UserPropertyType at, int change, int current, String funcName ){
        //{}依次为 用户名，物品类型，变化值，当前值，调用的方法名
        logger.info( "{},{},{},{},{}", uname, at, change, current, funcName );
    }

    public int getLevel(){
        return property.getLevel();
    }

    public boolean isAdult(){
        return property.isAdult();
    }

    public int getGold(){
        return property.getGold();
    }

    public String getNickName(){
        return property.getNickName();
    }

    public int getExp(){
        return property.getExp();
    }

    public int getCash(){
        return property.getCash();
    }
}
