package experiment.runcmd;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014/4/23 0023 22:20
 */

public class ScanFromNetstat{
    private static final String PORT_CMD = "cmd /c netstat -ano -p tcp|find /i \"listen\"";// + PORT_FILE;
    private static final String TASK_CMD = "cmd /c tasklist";

    public static void main( String[] args ) throws IOException, InterruptedException{

        ScanFromNetstat scan = new ScanFromNetstat();
        Map<Integer, String> map = scan.calcResult();

        System.out.println( "总共打开的端口数:" + map.size() );
        System.out.println( "进程名                      端口号" );
        System.out.println( "------                      ------" );
        int nameLen = 28;//对齐的空格长度
        for( Map.Entry<Integer, String> entry : map.entrySet() ) {
            String pName = entry.getValue();
            System.out.print( pName );
            for( int i = 0; i < nameLen - pName.length(); i++ ) {
                System.out.print( " " );
            }
            System.out.println( "" + entry.getKey() );
        }
    }

    /**
     * 通过tasklist命令获得的文件，<进程id,进程名>map
     *
     * @return <进程id,进程名>map
     */
    private Map<Integer, String> getPid(){
        List<String> list = runCmd( TASK_CMD );
        Map<Integer, String> map = Maps.newHashMap();
        int i = 0;
        for( String s : list ) {
            System.out.println( s );
            if( i++ < 3 ) {
                continue;
            }
//            Iterable<String> split = Splitter.on( "  " ).omitEmptyStrings().trimResults().split( s );//不支持文件名包含空格的情况
            int nameLen = 27, pidLen = 35;//进程名和pid各自的列长度
            String pName = s.substring( 0, nameLen );
            String temp = s.substring( nameLen, pidLen ).trim();
            int pid = Integer.parseInt( temp );
            map.put( pid, pName );
        }
        return map;
    }

    /**
     * @return <Integer,Integer> <端口,进程id>
     */
    private Map<Integer, Integer> getPort(){
        List<String> list = runCmd( PORT_CMD );

        Map<Integer, Integer> map = Maps.newHashMap();
        for( String s : list ) {
//            System.out.println( s );
            Iterable<String> split = Splitter.on( " " ).omitEmptyStrings().trimResults().split( s );
            List<String> temp = Lists.newArrayList( split );
//            System.out.println( temp );
            String portStr = temp.get( 1 );
            int pos = portStr.indexOf( ':' ) + 1;
            int port = Integer.parseInt( portStr.substring( pos, portStr.length() ) );
            int pid = Integer.parseInt( temp.get( temp.size() - 1 ) );
            map.put( port, pid );
            //map.put( Integer.parseInt( temp.get( 1 ) ), temp.get( 0 ) );

        }
        return map;
    }

    /**
     * 通过</>得到相应的结果<进程名，端口号>
     *
     * @return <进程名，端口号>
     */
    Map<Integer, String> calcResult(){
        Map<Integer, String> pid = getPid();
        Map<Integer, Integer> port = getPort();
        Map<Integer, String> result = Maps.newTreeMap();
        for( Map.Entry<Integer, Integer> entry : port.entrySet() ) {
            String pName = pid.get( entry.getValue() );
            result.put( entry.getKey(), pName );
        }
        return result;
    }

    /**
     * 执行命令，获取返回值
     *
     * @param cmd 要执行的dos命令
     * @return 执行结果按行返回List<String>
     */
    private List<String> runCmd( String cmd ){
        List<String> ret = Lists.newArrayList();
        try {
            Process process = Runtime.getRuntime().exec( cmd );
            BufferedInputStream in = new BufferedInputStream( process.getInputStream() );
            BufferedReader br = new BufferedReader( new InputStreamReader( in, "GBK" ) );
            String s;
            while( (s = br.readLine()) != null ) {
                ret.add( s );
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return ret;
    }
}
