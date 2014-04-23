package experiment.xsocket.scan;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import util.FileUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014/4/23 0023 22:20
 */

public class ScanFromNetstat{
    public static final String TASK_FILE = "e:/task.txt";
    public static final String PORT_FILE = "e:/port.txt";
    private static final String PORT_CMD = "cmd /c netstat -ano -p tcp|find /i \"listen\" >" + PORT_FILE;
    private static final String TASK_CMD = "cmd /c tasklist >" + TASK_FILE;


    /**
     * 通过tasklist命令获得的文件，<进程id,进程名>map
     *
     * @return <进程id,进程名>map
     */
    private Map<Integer,String> getPid(){
        Map<Integer, String> map = Maps.newHashMap();
        List<String> list = FileUtil.readList( TASK_FILE, Charset.forName( "GBK" ) );
        int i = 0;
        for( String s : list ) {
//            System.out.println( s );
            if( i++ < 4 ) {
                continue;
            }
            Iterable<String> split = Splitter.on( " " ).omitEmptyStrings().trimResults().split( s );
            List<String> temp = Lists.newArrayList( split );
            map.put( Integer.parseInt( temp.get( 1 ) ), temp.get( 0 ) );

        }
        return map;
    }

    /**
     *
     * @return  <Integer,Integer> <进程id，端口>
     */
    private Map<Integer, Integer> getPort(){
        Map<Integer, Integer> map = Maps.newHashMap();
        List<String> list = FileUtil.readList( PORT_FILE, Charset.forName( "GBK" ) );
        for( String s : list ) {
//
            Iterable<String> split = Splitter.on( " " ).omitEmptyStrings().trimResults().split( s );
            List<String> temp = Lists.newArrayList( split );
//            System.out.println( temp );
            String portStr = temp.get( 1 );
            int pos = portStr.indexOf( ':' ) + 1;
            int port = Integer.parseInt( portStr.substring( pos, portStr.length() ) );
            int pid = Integer.parseInt( temp.get( temp.size() - 1 ) );
            map.put( pid, port );
            //map.put( Integer.parseInt( temp.get( 1 ) ), temp.get( 0 ) );

        }
        return map;
    }

    /**
     * 通过</>得到相应的结果<进程名，端口号>
     * @return      <进程名，端口号>
     */
    Map<Integer,String> calcResult(){
        Map<Integer, String> pid = getPid();
        Map<Integer, Integer> port = getPort();
        Map<Integer, String> result = Maps.newHashMap();
        for( Map.Entry<Integer, Integer> entry : port.entrySet() ) {
            String pName = pid.get( entry.getKey() );
            result.put( entry.getValue(), pName );
        }
        return result;
    }

    /**
     * 扫尾工作，有必要的话，删除生成的临时文件
     */
    private void clear(){
        FileUtil.delFile( TASK_FILE );
        FileUtil.delFile( PORT_FILE );
    }

    public static void main( String[] args ) throws IOException, InterruptedException{
        Runtime.getRuntime().exec( PORT_CMD );
        Runtime.getRuntime().exec( TASK_CMD );
        Thread.sleep( 100 );

        Map<Integer, String> map;
        ScanFromNetstat scan = new ScanFromNetstat();
        map = scan.calcResult();
        System.out.println( map );
        scan.clear();

    }
}
