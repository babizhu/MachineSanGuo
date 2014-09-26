package com.bbz.sanguo.net.codec;

import com.google.protobuf.CodedOutputStream;

/**
 * user         LIUKUN
 * time         2014-9-26 11:44
 */

public class ProtobufTest{

    public static void main( String[] args ){
        int bodyLen = 127;
        int headerLen = CodedOutputStream.computeRawVarint32Size( bodyLen );

        System.out.println( headerLen);
//        int bodyLen = msg.readableBytes();
//        int headerLen = CodedOutputStream.computeRawVarint32Size( bodyLen );
//        out.ensureWritable(headerLen + bodyLen);
//
//        CodedOutputStream headerOut =
//                CodedOutputStream.newInstance(new ByteBufOutputStream(out));
//        headerOut.writeRawVarint32(bodyLen);
//        headerOut.flush();
//
//        out.writeBytes(msg, msg.readerIndex(), bodyLen);


    }
}
