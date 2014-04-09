package com.bbz.sanguo.ai.user.modules.recharge;

import com.bbz.util.db.IdentityObj;
import com.bbz.util.time.SystemTimer;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-9 17:53
 * <p/>
 * 充值记录
 */
@Data
public class RechargeRecord implements IdentityObj{

    /**
     * 充值流水号
     */
    private final long                  id;
    /**
     * 充值的钱数，rmb or dollar
     */
    private int                         money;

    /**
     * 充值时间
     */
    private int                         time;

    public RechargeRecord( long id, int money ){
        this.id = id;
        this.money = money;
        this.time = SystemTimer.currentTimeSecond();
    }

    public RechargeRecord( Long id ){
        this.id = id;
    }
}
