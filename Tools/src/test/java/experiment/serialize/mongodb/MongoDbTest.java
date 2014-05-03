package experiment.serialize.mongodb;

import com.mongodb.*;
import gen.util.D;
import org.junit.Before;
import org.junit.Test;
import util.db.MongoUtil;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * user         LIUKUN
 * time         2014-4-4 16:37
 * <p/>
 * Mongodb的一个测试程序
 */

public class MongoDbTest{
    MongoClient client;
    DBCollection coll;

    @Before
    public void setUp() throws Exception{
        client = MongoUtil.INSTANCE.getClient();
        coll = MongoUtil.INSTANCE.getDB().getCollection( D.TEST_DB );

    }

    //DBCursor cursor = coll.find(condition).addOption(Bytes.QUERYOPTION_NOTIMEOUT);//设置游标不要超时

    @Test
    /**
     * 获取所有数据库实例
     */
    public void testGetDBS(){
        List<String> dbnames = client.getDatabaseNames();
        for( String dbname : dbnames ) {
            System.out.println( "dbname:" + dbname );
        }
    }

    @Test
    /**
     * 删除数据库
     */
    public void dropDatabase(){
        client.dropDatabase( "test_db" );
    }

    @Test
    /**
     * 查询指定数据库所有表名
     */
    public void getAllCollections(){
        Set<String> colls = MongoUtil.INSTANCE.getDB().getCollectionNames();
        for( String s : colls ) {
            System.out.println( s );
        }
    }

    @Test
    /**
     * 删除一张表
     */
    public void dropCollection(){
        coll.drop();
    }

    /**
     * 添加一条记录
     */
    @Test
    public void addData(){
        BasicDBObject doc = new BasicDBObject();
        doc.put( "name", "MongoDB" );
        doc.put( "type", "database" );
        doc.put( "count", 1 );

        BasicDBObject info = new BasicDBObject();
        info.put( "x", 203 );
        info.put( "y", 102 );
        doc.put( "info", info );
        coll.insert( doc );
        // 设定write concern，以便操作失败时得到提示
        coll.setWriteConcern( WriteConcern.SAFE );
    }

    @Test
    /**
     * 创建索引
     */
    public void createIndex(){
        coll.createIndex( new BasicDBObject( "i", 1 ) );
    }

    @Test
    /**
     * 获取索引信息
     */
    public void getIndexInfo(){
        List<DBObject> list = coll.getIndexInfo();
        for( DBObject o : list ) {
            System.out.println( o );
        }
    }

    @Test
    /**
     * 添加多条记录，包括一个list
     */
    public void addMultiData(){
        DBCollection coll = MongoUtil.INSTANCE.getDB().getCollection( "test_db" );

        for( int i = 0; i < 100; i++ ) {
            coll.insert( new BasicDBObject().append( "i", i ) );
        }

        List<DBObject> docs = new ArrayList<>();
        for( int i = 0; i < 50; i++ ) {
            docs.add( new BasicDBObject().append( "i", i ) );
        }
        coll.insert( docs );
        // 设定write concern，以便操作失败时得到提示
        coll.setWriteConcern( WriteConcern.SAFE );
    }

    @Test
    /**
     * 查找第一条记录
     */
    public void findOne(){

        DBObject myDoc = coll.findOne();
        System.out.println( myDoc );
    }

    @Test
    /**
     * 获取表中所有记录条数
     */
    public void count(){
        System.out.println( coll.getCount() );
    }

    @Test
    /**
     * 获取查询结果集的记录数
     */
    public void getCount(){
        DBObject query = new BasicDBObject( "name", "a" );
        long count = coll.count( query );
        System.out.println( count );
    }

    @Test
    /**
     * 查询所有结果
     */
    public void getAllDocuments(){
        try( DBCursor cursor = coll.find() ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 按照一个条件查询
     */
    public void queryByConditionOne(){
        BasicDBObject query = new BasicDBObject();
        query.put( "name", "MongoDB" );

        try( DBCursor cursor = coll.find( query ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * AND多条件查询,区间查询
     */
    public void queryMulti(){
        BasicDBObject query = new BasicDBObject();
        // 查询j不等于3,k大于10的结果集
        query.put( "j", new BasicDBObject( "$ne", 3 ) );
        query.put( "k", new BasicDBObject( "$gt", 10 ) );
        try( DBCursor cursor = coll.find( query ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 区间查询
     * select * from table where i >50
     */
    public void queryMulti2(){
        BasicDBObject query = new BasicDBObject();
        query.put( "i", new BasicDBObject( "$gt", 50 ) ); // e.g. find all where i >
        try( DBCursor cursor = coll.find( query ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 区间查询
     * select * from table where 20 < i <= 30
     //比较符
     //"$gt"： 大于
     //"$gte"：大于等于
     //"$lt"： 小于
     //"$lte"：小于等于
     //"$in"： 包含
     */
    public void queryMulti3(){
        BasicDBObject query = new BasicDBObject();

        query.put( "i", new BasicDBObject( "$gt", 20 ).append( "$lte", 30 ) );
        try( DBCursor cursor = coll.find( query ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 组合in和and,如 select * from test_Table where (a=5 or b=6) and (c=5 or d = 6)
     */
    public void queryMulti4(){
        BasicDBObject query11 = new BasicDBObject();
        query11.put( "a", 1 );
        BasicDBObject query12 = new BasicDBObject();
        query12.put( "b", 2 );
        List<BasicDBObject> orQueryList1 = new ArrayList<>();
        orQueryList1.add( query11 );
        orQueryList1.add( query12 );
//        BasicDBObject orQuery1 = new BasicDBObject( "$or", orQueryList1 );
        BasicDBObject orQuery1 = new BasicDBObject( QueryOperators.OR, orQueryList1 );

        BasicDBObject query21 = new BasicDBObject();
        query21.put( "c", 5 );
        BasicDBObject query22 = new BasicDBObject();
        query22.put( "d", 6 );
        List<BasicDBObject> orQueryList2 = new ArrayList<>();
        orQueryList2.add( query21 );
        orQueryList2.add( query22 );
        BasicDBObject orQuery2 = new BasicDBObject( "$or", orQueryList2 );

        List<BasicDBObject> orQueryCombinationList = new ArrayList<>();
        orQueryCombinationList.add( orQuery1 );
        orQueryCombinationList.add( orQuery2 );

        BasicDBObject finalQuery = new BasicDBObject( "$and",
                orQueryCombinationList );
        try( DBCursor cursor = coll.find( finalQuery ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * IN查询
     * if i need to query name in (a,b); just use { name : { $in : ['a', 'b'] } }
     * select * from things where name='a' or name='b'
     * @param coll
     */
    public void queryIn(){
        BasicDBList values = new BasicDBList();
        values.add( "a" );
        values.add( "b" );
        BasicDBObject in = new BasicDBObject( "$in", values );
        try( DBCursor cursor = coll.find(
                new BasicDBObject( "name", in ) ) ) {

            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * "或"查询
     * select * from table where name  = '12' or times = 5
     * @param coll
     */
    public void queryOr(){
        insertData();
        QueryBuilder query = new QueryBuilder();
        query.or( new BasicDBObject( "name", "bbz1" ), new BasicDBObject( "times", 5 ) );
        // query.and( "id" ).is( 2 );
        try( DBCursor cursor = coll.find( query.get() ).addSpecial( "$returnKey", "" ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    private void insertData(){
        coll.drop();
        for( int i = 0; i < 100; i++ ) {
            DBObject obj = new BasicDBObject();
            obj.put( "_id", i );
            obj.put( "name", "bbz" + i );
            obj.put( "times", i );
            obj.put( "aid", i * 10 );
            coll.insert( obj );
        }
    }


    @Test
    /**
     * 查询定制的列
     */
    public void customQueryField() throws UnknownHostException{
        insertData();
        System.out.println( coll.count() );
//        Mongo mongo = new Mongo( "localhost", 27017 );
//        DB db = mongo.getDB( "zhongsou_ad" );
        BasicDBObjectBuilder bulder = new BasicDBObjectBuilder();
        bulder.add( "times", 1 );
        bulder.add( "aid", 1 );
        try( DBCursor cusor = coll.find( new BasicDBObject(), bulder.get() ) ) {
            for( DBObject dbObject : cusor ) {
                System.out.println( dbObject );
            }
        }
//        System.out.println( 1 );
    }

    @Test
    public void mapReduce() throws UnknownHostException{
//        Mongo mongo = new Mongo( "localhost", 27017 );
//        DB db = mongo.getDB( "zhongsou_ad" );
        /***
         *  book1 = {name : "Understanding JAVA", pages : 100}
         *  book2 = {name : "Understanding JSON", pages : 200}
         *  db.books.save(book1)
         *  db.books.save(book2)
         *  book = {name : "Understanding XML", pages : 300}
         *  db.books.save(book)
         *  book = {name : "Understanding Web Services", pages : 400}
         *  db.books.save(book)
         *  book = {name : "Understanding Axis2", pages : 150}
         *  db.books.save(book)
         *
         var map = function() {
         var category;
         if ( this.pages >= 250 )
         category = 'Big Books';
         else
         category = "Small Books";
         emit(category, {name: this.name});
         };
         var reduce = function(key, values) {
         var sum = 0;
         values.forEach(function(doc) {
         sum += 1;
         });
         return {books: sum};
         };
         var count  = db.books.mapReduce(map, reduce, {out: "book_results"});
         */
        try {

//            DBCollection books = db.getCollection( "books" );

            BasicDBObject book = new BasicDBObject();
            book.put( "name", "Understanding JAVA" );
            book.put( "pages", 100 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding JSON" );
            book.put( "pages", 200 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding XML" );
            book.put( "pages", 300 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding Web Services" );
            book.put( "pages", 400 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding Axis2" );
            book.put( "pages", 150 );
            coll.insert( book );

            String map = "function() { " +
                    "var category; " +
                    "if ( this.pages >= 250 ) " +
                    "category = 'Big Books'; " +
                    "else " +
                    "category = 'Small Books'; " +
                    "emit(category, {name: this.name});}";

            String reduce = "function(key, values) { " +
                    "var sum = 0; " +
                    "values.forEach(function(doc) { " +
                    "sum += 1; " +
                    "}); " +
                    "return {bookss: sum};} ";

            MapReduceCommand cmd = new MapReduceCommand( coll, map, reduce,
                    null, MapReduceCommand.OutputType.INLINE, null );

            MapReduceOutput out = coll.mapReduce( cmd );

            for( DBObject o : out.results() ) {
                System.out.println( o.toString() );
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * 自己学习mapreduce的例子
     *
     * @throws UnknownHostException
     */
    @Test
    public void mapReduce1() throws UnknownHostException{
//
        try {

//            DBCollection books = db.getCollection( "books" );
            coll.drop();
            System.out.println( "总数量" + coll.count() );
            BasicDBObject book = new BasicDBObject();
            book.put( "name", "Understanding JAVA" );
            book.put( "pages", 100 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding JSON" );
            book.put( "pages", 200 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding XML" );
            book.put( "pages", 300 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding Web Services" );
            book.put( "pages", 400 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Understanding Axis2" );
            book.put( "pages", 150 );
            coll.insert( book );

            book = new BasicDBObject();
            book.put( "name", "Effctive Java" );
            book.put( "pages", 150 );
            coll.insert( book );
            book = new BasicDBObject();
            book.put( "name", "Effctive Java1" );
            book.put( "pages", 150 );
            coll.insert( book );

            String map = "function() { " +
//                    "var category; " +
//                    "if ( this.pages >= 250 ) " +
//                    "category = 'Big Books'; " +
//                    "else " +
//                    "category = 'Small Books'; " +
                    "emit(this.pages, {count: 10});}";

            String reduce = "function(key, values) { " +
                    "var total = {\"urls\":[], \"sum\":0};   " +
//                    "values.forEach(function(doc) { " +
//                    "sum += 1; " +
//                    "}); " +
                    "for ( var i=0; i<values.length; i++ ) {" +
//                    "sum += values[i].count;" +
                    "total.sum += 1;" +
                    "total.urls.push(values[i])" +
                    "}" +
                    //"total.sum += values.length;" +
                    "return {count: total};} ";

            MapReduceCommand cmd = new MapReduceCommand( coll, map, reduce,
                    null, MapReduceCommand.OutputType.INLINE, null );

            MapReduceOutput out = coll.mapReduce( cmd );

            for( DBObject o : out.results() ) {
                System.out.println( o.toString() );
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }


    @Test
    public void GroupByManyField() throws UnknownHostException{
        //此方法没有运行成功
//        Mongo mongo = new Mongo( "localhost", 27017 );
//        DB db = mongo.getDB( "libary" );
//        DBCollection books = db.getCollection( "books" );
        BasicDBObject groupKeys = new BasicDBObject();
        groupKeys.put( "total", new BasicDBObject( "$sum", "pages" ) );

        BasicDBObject condition = new BasicDBObject();
        condition.append( "pages", new BasicDBObject().put( "$gt", 0 ) );


        String reduce = "function(key, values) { " +
                "var sum = 0; " +
                "values.forEach(function(doc) { " +
                "sum += 1; " +
                "}); " +
                "return {books: sum};} ";
        /**
         BasicDBList basicDBList = (BasicDBList)db.getCollection("mongodb中集合编码或者编码")
         .group(DBObject key,   --分组字段，即group by的字段
         DBObject cond,        --查询中where条件
         DBObject initial,     --初始化各字段的值
         String reduce,        --每个分组都需要执行的Function
         String finial         --终结Funciton对结果进行最终的处理
         */
//        DBObject obj = coll.group( groupKeys, condition, new BasicDBObject(), reduce );
//        System.out.println( obj );
//
//        AggregationOutput ouput = coll.aggregate( new BasicDBObject( "$group", groupKeys ) );
//        System.out.println( ouput.getCommandResult() );
//        System.out.println( coll.find( new BasicDBObject( "$group", groupKeys ) ) );
    }


    @Test
    /**
     * 分页查询
     */
    public void pageQuery(){

        insertData();
        try( DBCursor cursor = coll.find().skip( 0 ).limit( 10 ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 模糊查询,利用正则表达式
     */
    public void likeQuery(){
        insertData();
        Pattern john = Pattern.compile( "bbz?" );
        BasicDBObject query = new BasicDBObject( "name", john );

        // finds all people with "name" matching /joh?n/i
        try( DBCursor cursor = coll.find( query ) ) {
            while( cursor.hasNext() ) {
                System.out.println( cursor.next() );
            }
        }
    }

    @Test
    /**
     * 条件删除,并返回此数据
     */
    public void delete(){
        insertData();
        int count = (int) coll.count();
        BasicDBObject query = new BasicDBObject();
        query.put( "name", "bbz1" );
        // 找到并且删除，并返回删除的对象
        DBObject removeObj = coll.findAndRemove( query );
        System.out.println( removeObj );
        assertEquals( count - 1, coll.count() );
    }

    @Test
    /**
     * 更新
     */
    public void update(){
        BasicDBObject query = new BasicDBObject();
        query.put( "name", "liu" );
        coll.insert( query );
        DBObject stuFound = coll.findOne( query );
        stuFound.put( "name", stuFound.get( "name" ) + "update_1" );
        coll.update( query, stuFound );
    }

}
