package gen.cfg;

import gen.util.D;
import gen.util.TempletFile;
import gen.util.TempletType;
import util.FileUtil;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-12
 * Time: 下午4:54
 */

class GenXmlAndCfg {

    /**
     * 用于CfgInit.java的包导入部分
     */
    String packageImportStr = "";

    /**
     * 用于CfgInit.java的init函数部分
     */
    String initStr = "";

    public static void main( String[] args ){

    }

    void genAll( String path ){
        System.out.println( "遍历excel目录：" + path );
        File dir = new File( path );
        File[] files = dir.listFiles();

        if( files == null )
            return;
        for( File file : files ) {
            if( file.isDirectory() ) {
                genAll( file.getAbsolutePath() );
            } else {
                String strFileName = file.getAbsolutePath();
                genOne( strFileName );
                System.out.println( "处理完毕 " + strFileName );

            }
        }
        buildCfgInit();
    }

    /**
     * 生成CfgInit.java文件
     */
    private void buildCfgInit(){
        String file = "cfgInit.templet";
        TempletFile t = new TempletFile( TempletType.JAVA, file );
        String fileContent = t.getTempletStr();
        fileContent = fileContent.replace( "#pack#", packageImportStr )
                .replace( "#data#", initStr );
//        System.out.println(fileContent);
        String path = D.SRC_DIR + D.OUTPUT_CFG_DIR + "/CfgInit" + D.JAVA_FILE_SUFFIXES;
        FileUtil.writeTextFile( path, fileContent );


    }

    private void genOne( String realPath ){
        ParseExcel pe = new ParseExcel( realPath );
        pe.gen();
        String separator = System.getProperties().getProperty( "line.separator" );
        packageImportStr += pe.getImportStr() + separator;

        initStr += pe.getInitStr() + separator;

    }
}
