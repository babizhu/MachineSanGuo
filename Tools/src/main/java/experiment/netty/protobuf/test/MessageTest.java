package experiment.netty.protobuf.test;

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
        builder2.setCompany( 32 );
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
                    System.out.println( item );
                }
            }
        } catch( Exception e ) {


        }

    }

    private Object dispatch( MsgProtocol.Message msg ){
        switch( msg.getType() ) {
            case Login_Request:
                break;

        }
        return null;
    }

}
