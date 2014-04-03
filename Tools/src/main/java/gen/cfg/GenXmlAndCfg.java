package gen.cfg;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-12
 * Time: 下午4:54
 */

class GenXmlAndCfg {


    void genAll(String path) {
        System.out.println( "遍历excel目录：" + path );
        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                genAll(file.getAbsolutePath());
            } else {
                String strFileName = file.getAbsolutePath();
                genOne(strFileName);
                System.out.println("处理完毕 " + strFileName);

            }
        }
    }

    private void genOne(String realPath) {
        ParseExcel pe = new ParseExcel(realPath);
        pe.gen();
    }

    public static void main(String[] args) {

    }
}
