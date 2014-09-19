package com.bbz.sanguo.net.handler;

import com.bbz.sanguo.net.protobuf.MsgProtocol;
import com.bbz.tool.common.RandomUtil;

import static com.bbz.sanguo.net.protobuf.MsgProtocol.Request;
import static com.bbz.sanguo.net.protobuf.MsgProtocol.Response.Builder;


/**
 * user         LIUKUN
 * time         2014-5-29 14:57
 */

public class MissionShowHandler implements IHandlerWithUser{


    @Override
    public void run( Request request, Builder responseBuilder, Object user ){
        int missionId = request.getMissionShow().getMissionId();

        /****************************逻辑处理***************************/
        MsgProtocol.MissionShowResponse.Builder builder = MsgProtocol.MissionShowResponse.newBuilder();
        builder.setCd( 3 ).setCurrentMission( missionId ).setIsProtect( true ).setProtectCount( RandomUtil.getInt( 50 ) );

        /****************************设返回值***************************/

        responseBuilder.setMissionShow( builder );
    }
}
