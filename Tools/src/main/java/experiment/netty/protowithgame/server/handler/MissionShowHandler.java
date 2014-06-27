package experiment.netty.protowithgame.server.handler;

import com.bbz.tool.common.RandomUtil;
import experiment.protocolgen.MsgProtocol;

/**
 * user         LIUKUN
 * time         2014-5-29 14:57
 */

public class MissionShowHandler implements IHandlerWithUser{


    @Override
    public void run( MsgProtocol.Request request, MsgProtocol.Response.Builder responseBuilder, Object user ){
        int missionId = request.getMissionShow().getMissionId();

        /****************************逻辑处理***************************/
        MsgProtocol.MissionShowResponse.Builder builder = MsgProtocol.MissionShowResponse.newBuilder();
        builder.setCd( 3 ).setCurrentMission( missionId ).setIsProtect( true ).setProtectCount( RandomUtil.getInt( 50 ) );

        /****************************设返回值***************************/

        responseBuilder.setMissionShow( builder );
    }
}
