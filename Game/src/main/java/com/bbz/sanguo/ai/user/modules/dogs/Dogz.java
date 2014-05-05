package com.bbz.sanguo.ai.user.modules.dogs;

import com.bbz.tool.db.IdentityObj;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-15 18:37
 */
@Data
public class Dogz implements IdentityObj{

    /**
     * 唯一标识
     */
    private final long id;
    private int level;

}
