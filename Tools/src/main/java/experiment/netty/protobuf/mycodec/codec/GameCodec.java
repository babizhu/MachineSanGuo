package experiment.netty.protobuf.mycodec.codec;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import experiment.protocolgen.WorldClockProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-9-26 11:59
 *
 * 自定义的一个protobuf的解码器
 */

public class GameCodec extends ByteToMessageCodec<MessageLite>{

    private final boolean isServerSide;

    public GameCodec( boolean isServerSide ){
        this.isServerSide = isServerSide;
    }

    @Override
    protected void encode( ChannelHandlerContext ctx, MessageLite msg, ByteBuf out ) throws Exception{

        byte[] content = msg.toByteArray();
        System.out.print( "GameCodec.encode :" );
        for( int i = 0; i < content.length; i++ ) {
            System.out.print( content[i] + "," );
        }
        System.out.println();
        out.writeInt( content.length );
        out.writeBytes( content );


    }

    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List<Object> out ) throws Exception{
        in.markReaderIndex();
        if( in.readableBytes() < 4 ){
            return;
        }
        int len = in.readInt();
        if( in.readableBytes() < len ){
            in.resetReaderIndex();
            return;
        }
        byte[] array = new byte[len];
        in.readBytes( array );

        System.out.print( "GameCodec.decode:" );
        for( byte b : array ) {
            System.out.print( b + ",");
        }

        System.out.println();
//        byte[] array = new byte[in.readableBytes()];
//        in.getBytes(in.readerIndex(), array, 0, in.readableBytes());
//        for( byte b : array ) {
//            System.out.print( b + ",");
//        }

//        System.out.println( in.readableBytes() );
//        int len = in.readInt();
//        System.out.println( "len:" + len );
//        byte[] array;
//        array = new byte[len];
//        in.getBytes( in.readerIndex(), array, 0, len );
//        for( int i = 0; i < array.length; i++ ) {
//            System.out.print( array[i] + ",");
//        }
//        System.out.println("============================================================");
////        in.markReaderIndex();
////
////        if( in.readableBytes() < 4 ) {
////            return;
////        }
////        final int length = in.readInt();
////        if( in.readableBytes() < length ) {
////            in.resetReaderIndex();
////            return;
////        }
////
////
////
////        byte[] array;
////        int offset;
////        if (in.hasArray()) {
////            array = in.array();
////            //offset = in.arrayOffset() + in.readerIndex();
////        } else {
////            array = new byte[length];
////            in.getBytes(0, array, 0, length);
////            offset = 0;
////        }
////
////        System.out.println( "GameCodec.decode 长度为：" + array.length );
        if( isServerSide ) {

//            System.out.println( WorldClockProtocol.Locations.getDefaultInstance().getClass() );
            Parser<WorldClockProtocol.Locations> parserForType = WorldClockProtocol.Locations.getDefaultInstance().getParserForType();
            WorldClockProtocol.Locations locations = parserForType.parseFrom( array, 0, len );
            for( int i = 0; i < locations.getLocationCount(); i++ ) {
                System.out.println( locations.getLocation( i ).getCity() );
            }
            out.add( locations );


        }
        else{
            Parser<WorldClockProtocol.LocalTimes> parserForType = WorldClockProtocol.LocalTimes.getDefaultInstance().getParserForType();
            out.add( parserForType.parseFrom( array, 0, len ) );
        }

    }
}
