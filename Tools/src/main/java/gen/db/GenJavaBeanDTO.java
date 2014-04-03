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
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-21
 * Time: 下午2:54
 */
class GenJavaBeanDTO implements IGen{

    //String path = D.SRC_DIR + "gen/db/";
    private final Table table;
    private String src;

    public GenJavaBeanDTO(Table table){
        this.table = table;
        src = new TempletFile(TempletType.DB, "beanDTO.t").getTempletStr();
    }


    @Override
    public void gen(){

        src = src.
                replace(D.PACAKAGE_NAME_TAG, D.OUTPUT_DB_DTO_DIR.replace("/", ".")).
                replace(D.CLASS_NAME_TAG, genClassName()).
                replace(D.DATE_TAG, DateFormat.getDateTimeInstance().format(new Date())).
                replace(D.FIELD_AREA_TAG, genFiledS()).
                replace(D.TO_STRING_TAG, genToString());

//        System.out.println(src);
        FileUtil.writeTextFile(D.SRC_DIR + D.OUTPUT_DB_DTO_DIR + "/" + genClassName() + D.JAVA_FILE_SUFFIXES, src);

    }

    private String genFiledS(){
        StringBuilder sb = new StringBuilder();

        for( Column column : table.getColumns() ) {
            if( !column.getName().equals("uname") || !table.getKeys().contains(column) ) {    //用作主键的uname不生成bean
                sb.append(genField(column));
            }
        }
        return sb.toString();
    }

    private String genField(Column column){
        String ret = new TempletFile(TempletType.DB, "fieldTemplet.t").getTempletStr();
        ret = ret.
                replace(D.ANNOTATION_TAG, column.getAnnotation()).
                replace(D.FIELD_TYPE_TAG, DataTransUtil.getTypeFromDb(column.getType())).
                replace(D.FIELD_TAG, column.getName()).
                replace(D.METHOD_NAME_GET_TAG, Util.genGet(column.getName())).
                replace(D.METHOD_NAME_SET_TAG, Util.genSet(column.getName()));

        return ret;
    }

    public String genClassName(){
        return Util.firstToUpperCase(table.getName());
    }

    /**
     * 生成toString方法
     */
    private String genToString(){

        StringBuilder sb = new StringBuilder();
        for( Column column : table.getColumns() ) {
            if( !column.getName().equals("uname") || !table.getKeys().contains(column) ) {    //用作主键的uname不生成bean
                String temp = column.getName() + " = \" + " + column.getName() + " + \",";
                sb.append(temp);
            }
        }
        return sb.substring(0, sb.length() - 5);
    }

    public static void main(String[] args){

        Table table = MetaData.INSTANCE.getTableByName("invite");
        new GenJavaBeanDTO(table).gen();
    }

}




