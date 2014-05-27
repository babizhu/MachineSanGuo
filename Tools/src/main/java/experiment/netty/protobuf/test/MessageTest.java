package experiment.netty.protobuf.test;

import experiment.netty.protobuf.test.handler.LoginHandler;
import experiment.protocolgen.MsgProtocol;

/**
 * user         LIUKUN
 * time         2014-5-26 17:44
 */

public class MessageTest{

    public static void main( String[] args ){
        register( null );

    }

    public static void response( String[] args ){

        MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
        MsgProtocol.Response.Builder builder1 = MsgProtocol.Response.newBuilder();
        MsgProtocol.LoginResponse.Builder builder2 = MsgProtocol.LoginResponse.newBuilder();
        builder2.setRet( 32 );
        builder1.setLogin( builder2.build() );
        builder.setResponse( builder1.build() );
    }

    public static void register( Class<?> protobufclass ){
        try {
            ProtoMessageHandler mb;
            Class<?>[] protoObjs = MsgProtocol.class.getClasses();
            for( Class<?> item : protoObjs ) {
                if( item == null )
                    continue;
                if( !item.isInterface() && item.getSuperclass().equals(
                        com.google.protobuf.GeneratedMessage.class ) ) {
                    mb = new ProtoMessageHandler();
                    mb.SetType( item );
                    //mMessageTbl.put(item.getSimpleName(), mb);
                    System.out.println( item.getSimpleName() );
                }
            }
        } catch( Exception e ) {


        }

    }

    private void excute(){
        MsgProtocol.Message.Builder builder = MsgProtocol.Message.newBuilder();
        builder.setResponse( dispatch( null ) );
        builder.setSequence( 120 );
        builder.setType( MsgProtocol.MSG.Login_Response );


    }

    private MsgProtocol.Response.Builder dispatch( MsgProtocol.Message msg ){
        MsgProtocol.Response.Builder result = MsgProtocol.Response.newBuilder();


        try {
            switch( msg.getType() ) {
                case Login_Request:
                    result.setLogin( (MsgProtocol.LoginResponse) new LoginHandler().run( msg.getRequest().getLogin() ) );
                    break;
                case Login_Response:
                    break;
                //case

            }
            result.setResult( true );
        } catch( Exception e ) {
            result.setResult( false );
            result.setErrorDescription( "出错啦" );
        }

        msg.getType().getDescriptorForType();
        return result;
    }

}
