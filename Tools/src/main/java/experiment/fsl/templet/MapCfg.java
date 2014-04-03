package experiment.fsl.templet;

import com.google.common.collect.Maps;
import util.FileUtil;

import java.io.File;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-18
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class MapCfg{
    private static final String PATH = "";
    private static final Map<Integer, MapTemplet> templets = Maps.newHashMap();


    public MapTemplet getMapTemplet(int id){
        return templets.get(id);
    }

    static{
        init();
    }

    private static void init(){
        File dir = new File(PATH);
        File[] files = dir.listFiles();

        if( files == null )
            return;
        for( File file : files ) {
            if( !file.isDirectory() ) {
                String fileName = file.getAbsolutePath();
                String json = FileUtil.readTextFile(fileName);
                MapTemplet t = new MapTemplet(json);
                int key = getKey(fileName);
                templets.put(key, t);
                System.out.println("处理完毕 " + fileName);

            }
        }
    }

    /**
     * 通过文件名获取key
     *
     * @param fileName 文件名为1.json就应该返回1，
     * @return
     */
    private static int getKey(String fileName){
        int pos = fileName.lastIndexOf("\\");
        return Integer.parseInt(fileName.substring(pos));
    }

    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        sb.append("a").append("b");
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);

    }

}
