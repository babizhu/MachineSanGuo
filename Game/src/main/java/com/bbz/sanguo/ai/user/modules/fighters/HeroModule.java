package com.bbz.sanguo.ai.user.modules.fighters;

import com.bbz.sanguo.ai.ClientException;
import com.bbz.sanguo.ai.ErrorCode;

import java.util.List;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:52
 * 英雄管理类
 */
public class HeroModule{

    private final HeroDataProvider db;

    /**
     * 所有的英雄
     */
    private List<Hero> heros;

    public HeroModule( String uname ){
        db = new HeroDataProvider( uname );
        heros = db.getListAll();
    }

    public static void main( String[] args ){
    }

    /**
     * 英雄升级
     *
     * @param heroId 要升级的英雄
     */
    public void levelUp( int heroId ){
        Hero hero = getHeroById( heroId );
        if( hero == null ) {
            throw new ClientException( ErrorCode.HERO_NOT_FOUND );
        }
        hero.levelUp();
    }

    private Hero getHeroById( long heroId ){
        for( Hero hero : heros ) {
            if( hero.getId() == heroId ) {
                return hero;
            }
        }
        return null;
    }

}
