package com.bbz.util.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * user         LIUKUN
 * time         14-3-27 下午3:51
 * <p/>
 * 负责处理具有唯一id的实体类,构造函数包括2个，一个用户名，一个表名
 */

public abstract class AbstractDataProviderWithUserName<T>{
    protected final String uname;
    protected final DBCollection collection;

    /**
     * @param tableName 要处理的表名
     * @param uname     玩家名称
     */
    public AbstractDataProviderWithUserName( String tableName, String uname ){
        this.uname = uname;
        collection = MongoUtil.INSTANCE.getDB().getCollection( tableName );
    }

    /**
     * 通过username进行查找
     *
     * @return 玩家信息
     */
    public T findOne(){
        DBObject condition = new BasicDBObject( "_id", uname );
        return decode( collection.findOne( condition ) );
    }

//    public T findOne( DBObject condition ){
//        return decode( collection.findOne( condition ) );
//    }

//    public List<T> findBy( String key, Object content ){
//        BasicDBObject condition = new BasicDBObject( "uname", uname );
//        condition.append( key, content );
//        return findBy( condition );
//    }
//
//    public List<T> findBy( DBObject condition ){
//        List<T> list = Lists.newArrayList();
//
//        DBCursor cursor = collection.find( condition );
//        while( cursor.hasNext() ) {
//            list.add( decode( cursor.next() ) );
//        }
//        return list;
//    }

//    public void add( T t ){
//        collection.insert( encode( t ) );
//    }

//    public List<T> getListAll(){
//        List<T> list = Lists.newArrayList();
//
//        BasicDBObject condition = new BasicDBObject( "uname", uname );
//
//        try( DBCursor cursor = collection.find( condition ) ) {
//            while( cursor.hasNext() ) {
////            cursor.next();
//                list.add( decode( cursor.next() ) );
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 以id为key返回一个hashmap
//     *
//     * @return
//     */
//    public Map<Long, T> getMapAll(){
//        Map<Long, T> map = Maps.newHashMap();
//
//        BasicDBObject condition = new BasicDBObject( "uname", uname );
//
//        try( DBCursor cursor = collection.find( condition ) ) {
//            while( cursor.hasNext() ) {
//                T t = decode( cursor.next() );
////            cursor.next();
//                map.put( t.getId(), t );
//            }
//        }
//        return map;
//    }

    protected abstract T decode( DBObject object );

    protected abstract DBObject encode( T t );

    /**
     * 删除该用户下所有的信息
     */
    public void remove(){
        DBObject conditon = new BasicDBObject( "_id", uname );
        collection.remove( conditon );
    }

//    public void update( T t ){
//        DBObject conditon = new BasicDBObject( "_id", uname );
//        collection.update( conditon, encode( t ) );
//    }

    /**
     * 更新一个对象的某个字段
     *
     * @param fieldName  要更新的列名
     * @param fieldValue 要更新的内容
     */
    public void updateWithField( String fieldName, Object fieldValue ){
        BasicDBObject condition = new BasicDBObject( "_id", uname );
        BasicDBObject updateField = new BasicDBObject( "$set", new BasicDBObject( fieldName, fieldValue ) );
        collection.updateMulti( condition, updateField );
    }

    /**
     * * 删除此表下的所有数据,慎用！！！！！！！！！！！！！！！！！
     *
     * @param isRemoveAllData 确定要删除整个表的内容吗？
     */
    @SuppressWarnings("UnusedDeclaration")
    public void removeAll( boolean isRemoveAllData ){
        collection.drop();
    }

//    /**
//     * 删除表中所有的数据
//     * 慎用！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
//     */
//    public void drop(){
//        collection.drop();
//    }

    /**
     * 如果数据库存在此数据("_id"为标准)，则更新此数据，否则添加此数据
     *
     * @param t 要更新的数据
     * @return true:替换</br>
     * false:插入
     */
    public boolean replace( T t ){
        WriteResult save = collection.save( encode( t ) );
        return (Boolean) save.getField( "updatedExisting" );
    }

    public String getUname(){
        return uname;
    }

    public DBCollection getCollection(){
        return collection;
    }
}
