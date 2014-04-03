package gen.db;

import gen.db.dto.Column;
import gen.db.dto.Table;
import util.db.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库元数据类，采用单例设计模式，为其他类提供数据库元数据信息
 *
 * @author dengzongrong
 * @version 1.1
 *          2011-10-09
 */
enum MetaData{

    INSTANCE();

    MetaData(){
        getTables();
    }

    List<Table> tables;


//	private static MetaData instance = null;
//	private static Connection conn;
//	private static String driver;
//	private static String url;
//	private static String user;
//	private static String pass;

//	/**
//	 * 私有构造方法，保证这个类生成的对象只有一个
//	 */
//	MetaData() {
//		try {
//
//			driver = getProperty("driver");
//			url = getProperty("url");
//			user = getProperty("uname");
//			pass = getProperty("pwd");
//
//			Class.forName(driver);
//
//			conn = DriverManager.getConnection(url, user, pass);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private String getProperty(String attrbute) {
//		Element root = PropertiesLoader.getRoot();
//		return root.attributeValue(attrbute);
//	}
//
//	/**
//	 * 获得这个类唯一的对象
//	 * @return instance
//	 */
//	public static MetaData getInstance() {
//		if (instance == null) {
//			synchronized (MetaData.class) {
//				if (instance == null) {
//					instance = new MetaData();
//				}
//			}
//		}
//		return instance;
//	}

    /**
     * 获取该数据库的所有表名
     *
     * @return List
     * <Table>
     */
    public List<Table> getTables(){
        Connection con = DatabaseUtil.INSTANCE.getConnection();

        ResultSet rs = null;
        Statement stat = null;
        ResultSet pkrs;
        ResultSet trs;
        try {
            tables = new ArrayList<Table>();
            DatabaseMetaData meta = con.getMetaData();
            trs = meta.getTables(null, null, null, new String[]{"TABLE"});
            while( trs.next() ) {
                Table table = new Table();

                //设置表名
                table.setName(trs.getString(3));


                //设置表主键的集合
                pkrs = meta.getPrimaryKeys(null, null, trs.getString(3));
                while( pkrs.next() ) {
                    Column key = this.getColumnByColumnName(table.getName(), pkrs.getString(4));
                    //					table.getKeys().add(label);
                    table.addKey(key);
                }
                //设置表所有列的集合

                stat = con.createStatement();

//				System.out.println("asdfasdetq43124: " + "select * from " + trs.getString(3));
                rs = stat.executeQuery(String.format("select * from %s limit 1", trs.getString(3)));
                ResultSetMetaData columnsMeta = rs.getMetaData();

                for( int i = 1; i <= columnsMeta.getColumnCount(); i++ ) {
                    Column column = new Column();
                    column.setName(columnsMeta.getColumnLabel(i));
                    column.setType(columnsMeta.getColumnType(i));
                    column.setTypeName(columnsMeta.getColumnTypeName(i));
                    column.setAnnotation(this.getAnnotation(table.getName(), column.getName()));
                    table.getColumns().add(column);
                }

//				Document doc = PropertiesLoader.getDoc();
//				Element root = doc.getRootElement();
//				Element db = root.element("db");
//				Element generate_table = db.element("generate_table");
//				
//				List<Element> ts = generate_table.elements("table");

//				if( ts.isEmpty() ){
                tables.add(table);
//				} else {
//					for (Element tb : ts) {
//						String tableName = tb.attributeValue("name");
//						String findBy = tb.attributeValue("findby");
//						String [] findByFields = new String[0];
//						if(findBy!= null) {
//							findByFields = findBy.split(",");
//						}
//						
//						if(tableName.equals( table.getName())) {
//							table.addFindByMethod(findByFields);
//							tables.add( table );
//						}
//					}
//				}
            }

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.INSTANCE.close(rs, stat, con);
//			Closer.close(rs, stat);
//			Closer.close(pkrs);
//			Closer.close(trs);
        }
        return tables;
    }

    /**
     * 根据表名获取该表的所有字段名称和字段类型
     *
     * @param tableName 表名
     * @return 由表明和类型组成的LabelDTO的List
     */
//    List<Column> getColumnsByTableName(String tableName) {
//        Connection con = DatabaseUtil.INSTANCE.getConnection();
//        List<Column> columnS = null;
//        ResultSet rs = null;
//        Statement stat = null;
//        try {
//            columnS = new ArrayList<Column>();
//            stat = con.createStatement();
//            rs = stat.executeQuery("select * from " + tableName);
//            ResultSetMetaData meta = rs.getMetaData();
//            for (int i = 1; i <= meta.getColumnCount(); i++) {
//                Column label = new Column();
//                label.setName(meta.getColumnLabel(i));
//                label.setType(meta.getColumnType(i));
//                label.setTypeName(meta.getColumnTypeName(i));
//                columnS.add(label);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DatabaseUtil.INSTANCE.close(rs, stat, con);
//        }
//        return columnS;
//    }

    /**
     * 根据列名和表名获取该列的详细信息
     *
     * @param columnName 列名
     * @param tableName  表名
     * @return LabelDTO
     */
    private Column getColumnByColumnName(String tableName, String columnName){
        Connection con = DatabaseUtil.INSTANCE.getConnection();
        Column column = null;
        ResultSet rs = null;
        Statement stat = null;
        try {
            column = new Column();
            stat = con.createStatement();
//			System.out.println("select " + columnName + " from "
//					+ tableName);
            rs = stat.executeQuery(String.format("select %s from %s", columnName, tableName));
            ResultSetMetaData meta = rs.getMetaData();
            column.setName(meta.getColumnLabel(1));
            column.setType(meta.getColumnType(1));
            column.setTypeName(meta.getColumnTypeName(1));
        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.INSTANCE.close(rs, stat, con);
        }
        return column;
    }


    /**
     * 返回字段的注释，注：仅适用于mysql
     *
     * @param tableName  表名
     * @param columnName 字段名
     * @return 返回注释
     */
    private String getAnnotation(String tableName, String columnName){
        Connection con = DatabaseUtil.INSTANCE.getConnection();

        ResultSet rs = null;
        Statement stat = null;
        try {
            stat = con.createStatement();
//
            String sql = String.format("Select COLUMN_COMMENT from INFORMATION_SCHEMA.COLUMNS Where table_name = '%s' AND column_name = '%s'", tableName, columnName);
            rs = stat.executeQuery(sql);
            rs.next();

            return rs.getString(1);
        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.INSTANCE.close(rs, stat, con);
        }
        return null;
    }

    public Table getTableByName(String tableName){
        for( Table t : tables ) {
            if( t.getName().equals(tableName) ) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println("annotation is" + INSTANCE.getAnnotation("invite", "count"));
        System.out.println(INSTANCE.getTables());
    }
}
