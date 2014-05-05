package com.bbz.sanguo.ai.user.modules.dogs;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         14-3-26 下午2:38
 */

public class DogzDataProvider extends AbstractDataProviderWithIdentity<Dogz>{

    private static final String TABLE_NAME = "dogz";

    public DogzDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }


    @Override
    protected Dogz decode( DBObject object ){
        return null;
    }

    @Override
    protected DBObject encode( Dogz dogz ){
        return null;
    }
}