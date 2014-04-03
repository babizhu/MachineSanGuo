package gen.db;

import gen.db.dto.Column;
import gen.db.dto.Table;
import gen.db.util.DataTransUtil;
import gen.util.D;
import gen.util.TempletFile;
import gen.util.TempletType;
import gen.util.Util;
import util.FileUtil;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-22
 * Time: 下午5:07
 * <p/>
 * 生成数据库某张表的privider类
 */
class GenProvider implements IGen{
    Table table;
    private final String className;
    private final String packageName;
    private final String DTOClassName;
    private final String DTOClassParam;
    private String src;

    public GenProvider(Table table){
        this.table = table;
        packageName = D.OUTPUT_DB_PROVIDER_DIR.replace("/", ".");
        DTOClassName = Util.firstToUpperCase(table.getName());
        DTOClassParam = Util.firstToLowCase(DTOClassName);
        className = genClassName();
        src = new TempletFile(TempletType.DB, "provider.t").getTempletStr();
    }

    @Override
    public void gen(){
        genMisc();
        src = src.replace(D.TABLE_NAME_TAG, table.getName());
        genAdd();
        genPstAdd();
        genMapping();
        genAddAll();
        genDelete();
        genUpdate();
//        System.out.println(src);

        FileUtil.writeTextFile(D.SRC_DIR + D.OUTPUT_DB_PROVIDER_DIR + "/" + genClassName() + D.JAVA_FILE_SUFFIXES, src);
    }

    private void genPstAdd(){

        List<Column> columns = table.getColumns();

        src = src.replace(D.PST_ADD_TAG, genPst(columns));
    }

    private String genPst(List<Column> columns){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for( Column c : columns ) {
            if( c.getName().equals("uname") && table.getKeys().contains(c) ) {
                sb.append(String.format("pst.setString( %d, userName );", i++));
            } else {
                sb.append("pst.set").append(DataTransUtil.getTypeFromDb1(c.getType())).// pst.setShort
                        append("( ").append(i++).append(", ").//pst.setShort( 1,
                        append(DTOClassParam).append(".").append(Util.genGet(c.getName())).//pst.setShort( 1, task.getNumber
                        append("() );\r\n"); //pst.setShort( 1, task.getNumber() );
            }
        }
        return sb.toString();
    }

    /**
     * 生成AddAll代码段
     */
    private void genAddAll(){
        List<Column> columns = table.getColumns();
        //String sql = String.format("'%s',%s", user.getName, user.getLevels() );
        StringBuilder sb = new StringBuilder("String.format( \"");

        for( Column c : columns ) {
            if( DataTransUtil.getTypeFromDb(c.getType()).equals("String") )//字符串
                sb.append("'%s',");
            else
                sb.append("%s,");
        }
        if( columns.size() > 0 ) {
            sb.deleteCharAt(sb.length() - 1);//去掉逗号
        }
        sb.append("\", ");//String sql = String.format("%s,%s",
        for( Column c : columns ) {
            if( c.getName().equals("uname") && table.getKeys().contains(c) ) {
                sb.append("userName,");
            } else {
                sb.append(this.DTOClassParam).append(".").//user.
                        append(Util.genGet(c.getName())).append("(),");//user.getName(),
            }
        }
        if( columns.size() > 0 ) {
            sb.deleteCharAt(sb.length() - 1);//去掉最后的逗号
        }
        sb.append(")");

        src = src.replace(D.ADD_ALL_TAG, sb);

    }

    private void genMapping(){
        List<Column> columns = table.getColumns();
        StringBuilder sb = new StringBuilder();
        int i = 1;

        //user.setLevel( rs.getShort("level") );
        for( Column c : columns ) {
            if( c.getName().equals("uname") && table.getKeys().contains(c) ) {
                i++;

            } else {
                sb.append(this.DTOClassParam).append(".").//user.
                        append(Util.genSet(c.getName())).append("( rs.get").//user.setLevel( rs.get
                        append(DataTransUtil.getTypeFromDb1(c.getType())).append("( ").//user.setLevel( rs.getShort(
                        append(i++).//user.setLevel( rs.getShort( 1
                        //append( "\"").append(c.getName()).append( "\"").考虑效率这里用数字代替，如果有问题，在切换回来常规方式
                        append(" ) );\r\n");//user.setLevel( rs.getShort(1) );
            }

        }
        src = src.replace(D.RS_MAPPING_TAG, sb);
    }

    private void genKeyCondition(){
        List<Column> keys = table.getKeys();
        StringBuilder sb = new StringBuilder();
        for( Column c : keys ) {
            sb.append(c.getName()).append("=? and ");
        }
        if( keys.size() > 0 ) {
            sb.delete(sb.length() - 5, sb.length());
        }
        src = src.replace(D.KEY_CONDITION_TAG, sb);
    }

    private void genDelete(){

        src = src.replace(D.PST_DELETE_TAG, genPst(table.getKeys()));
    }

    private void genUpdate(){
        List<Column> list = new ArrayList<Column>(table.getColumns());
        list.removeAll(table.getKeys());

        StringBuilder sb = new StringBuilder();
        for( Column c : list ) {
            sb.append(c.getName()).append("=?,");
        }
        if( list.size() > 0 ) {
            sb.delete(sb.length() - 1, sb.length());
        }

        StringBuilder pst = new StringBuilder();
        list.addAll(table.getKeys());
        pst.append(genPst(list));

        src = src.replace(D.PST_UPDATE_TAG, pst).
                replace(D.PST_UPDATE_COLUMNS_TAG, sb);

    }

    /**
     * 生成add sql语句
     * insert into task_base (uname, templet_id, accept_sec, parm) "  + "values (?, ?, ?, ?)";
     */
    private void genAdd(){
        List<Column> columns = table.getColumns();
        StringBuilder sb = new StringBuilder();
        for( Column c : columns ) {
            sb.append(c.getName()).append(",");
        }

        StringBuilder sb1 = new StringBuilder();
        for( int i = 0; i < columns.size(); i++ ) {
            sb1.append("?,");
        }
        if( columns.size() > 0 ) {
            sb.deleteCharAt(sb.length() - 1);//去掉最后的逗号
            sb1.deleteCharAt(sb1.length() - 1);//去掉最后的逗号
        }
        src = src.replace(D.COLUMN_NAME_TAG, sb).
                replace(D.COLUMN_QUESTION_MARK_TAG, sb1);

    }

    private void genMisc(){
        src = src.
                replace(D.PACAKAGE_NAME_TAG, packageName).
                replace(D.DATE_TAG, DateFormat.getDateTimeInstance().format(new Date())).
                replace(D.CLASS_NAME_TAG, className).
                replace(D.DTO_CLASS_NAME_TAG, DTOClassName).
                replace(D.DTO_CLASS_PARAM_TAG, DTOClassParam);
        genKeyCondition();

    }

    private String genClassName(){
        return Util.firstToUpperCase(table.getName()) + "DataProvider";
    }
}
