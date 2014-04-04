package experiment.serialize.mongodb;

import com.mongodb.*;
import util.db.MongoUtil;

import java.net.UnknownHostException;

/**
 * user         LIUKUN
 * time         14-3-25 下午5:17
 * <p/>
 * Mongo的一些功能的测试程序
 */

public class MongoDb{
    private static String TABLE_NAME = "hero";
    private static DBCollection collection = MongoUtil.INSTANCE.getDB().getCollection( TABLE_NAME );

    /**
     * 更新符合条件的记录的某个字段的值，类似update tabel set field1 = 'x' where condition=true;
     */
    static void testUpdate(){

//        collection.drop();
//        collection.insert( new BasicDBObject( "name", "liukun" ).append( "count", 34 ) );
//        collection.insert( new BasicDBObject( "name", "js" ).append( "count", 134 ) );
//        print();

//        collection.updateMulti( new BasicDBObject( "count", new BasicDBObject( "$gt", 1 ) ), new BasicDBObject( "$set", new BasicDBObject( "name", "a" ) ) );
        collection.updateMulti( new BasicDBObject( "uname", "lk" ), new BasicDBObject( "$set", new BasicDBObject( "templetId", 400137 ) ) );
//        db.t1.update({"count":{$gt:1}},{$set:{"test2":"OK1"}})
        System.out.println( collection.count() );
        print();
    }



    static void test(){
        collection.drop();
        DBObject obj = new BasicDBObject( "name", "liukun" ).append( "_id", 123 );

        WriteResult save = collection.save( obj );
        System.out.println( save );
        System.out.println( save.getField( "updatedExisting" ) );

        System.out.println( collection.find().count() );

        save = collection.save( obj );
        System.out.println( save );
        System.out.println( save.getField( "updatedExisting" ) );
        System.out.println( collection.find().count() );

    }

    static void print(){
        for( DBObject object : collection.find() ) {
            System.out.println( object );
        }
        ;
    }


    public static void main( String[] args ) throws UnknownHostException{
        testUpdate();
        //test();

    }
}
