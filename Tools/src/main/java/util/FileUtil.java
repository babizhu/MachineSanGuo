package util;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-11
 * Time: 下午4:11
 * 文件操作的工具类
 */
public class FileUtil{
    /**
     * 读取path文件的所有内容到一个List<String>中
     *
     * @param path 要读取的文本路径
     * @return list
     */
    public static List<String> readList(String path){
        File file = new File(path);
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch( IOException e ) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * 读取path文件的所有内容到一个String中
     *
     * @param path 要读取的文本路径
     * @return 文本内容
     */
    public static String readTextFile(String path){
        String content = null;
        File file = new File(path);
        try {
            content = Files.toString(file, Charset.defaultCharset());

        } catch( IOException e ) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * 把content的内容写入文件
     *
     * @param path    路径
     * @param content 内容
     *                <br/>
     *                <p/>
     *                如果文件已经存在，则会覆盖文件<br/>
     *                如果目录路径不存在会自动创建
     */
    public static void writeTextFile(String path, String content){
        File file = new File(path);
        if( !file.exists() ) {
            File p = new File(path.substring(0, path.lastIndexOf('/')));
            p.mkdirs();
        }
        try {
            Files.write(content, file, Charset.defaultCharset());
        } catch( IOException e ) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{

        URL url = Resources.getResource("templet/cfg/fieldTemplet.t");
        String content = Resources.toString(url, Charset.defaultCharset());

//        System.out.println(content);


//        System.out.println( FileUtil.INSTANCE.readList( "/test.txt" ) );
//        String c = FileUtil.readTextFile("c:/1.txt");
//        FileUtil.writeTextFile("c:/sd/2.txt", c);
//        System.out.println( new StringBuilder( "abcd" ).reverse() );


    }
}
