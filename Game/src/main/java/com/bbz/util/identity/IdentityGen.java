package com.bbz.util.identity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * user         LIUKUN
 * time         2014-3-28 17:11
 *
 * 唯一id生成器，整个系统需要唯一id的时候都从这里取
 *
 */

public enum IdentityGen{
    INSTANCE;
    private final AtomicLong      gen;

    IdentityGen( ){
        long lastId = IdentityDataProvider.INSTANCE.get();
        if( lastId == 0 ){
            lastId = init();
        }
        this.gen = new AtomicLong( lastId );
    }

    /**
     * 根据游戏区初始化生成相应的id，算法为区id * 50000000000
     * 比如第一区的初始id可能为0
     * 比如第二区的初始id可能为50000000000
     * 比如第三区的初始id可能为50000000000 * 2
     * @return
     */
    private long init(){
        return 0;
    }

    public long incrementAndGet(){
        long id = gen.incrementAndGet();
        IdentityDataProvider.INSTANCE.set( id );
        return id;
    }
}
