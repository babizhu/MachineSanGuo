package com.bbz.util.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * user         LIUKUN
 * time         14-3-25 下午7:47
 */

public enum MongoUtil{
    INSTANCE;

    private MongoClient client;
    private DB db;

    MongoUtil(){
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream( new FileInputStream( "resource/mongo.properties" ) );
            prop.load( in );

            String ip = prop.getProperty( "ip" ).trim();
            int port = Integer.parseInt( prop.getProperty( "port" ).trim() );
            String dbName = prop.getProperty( "dbName" ).trim();

            ServerAddress serverAddress = new ServerAddress( ip, port );
            client = new MongoClient( serverAddress );
            db = client.getDB( dbName );
        } catch( IOException e ) {
            e.printStackTrace();
        }

//        ResourceBundle bundle = ResourceBundle.getBundle( "mongo" );
//        if( bundle == null ) {
//            throw new IllegalArgumentException(
//                    "[mongo.properties] is not found!" );
//        }
//        String ip = bundle.getString( "ip" );
//        int port = Integer.parseInt( bundle.getString( "port" ) );
//        String dbName = bundle.getString( "dbName" );
//        ServerAddress serverAddress = null;
//        try {
//            serverAddress = new ServerAddress( ip, port );
//        } catch( UnknownHostException e ) {
//            e.printStackTrace();
//        }


    }

    public DB getDB(){
        return db;
    }

    public static void main( String[] args ){
        System.out.println( 1 );
    }

}
