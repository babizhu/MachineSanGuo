package com.bbz.sanguo.ai.user;

/**
 * user         LIUKUN
 * time         2014-4-9 18:02
 */

public class User{
    public ModuleManager getModuleManager(){
        return moduleManager;
    }

    private final ModuleManager   moduleManager;

    public User( String uname ){
        this.moduleManager = new ModuleManager( uname );
    }
}
