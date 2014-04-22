package experiment.guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-6
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
public class FileTest{


    public static void main( String[] args ) throws IOException{
        new FileTest().readFile();
    }

    void readFile(){
        File file = new File( getClass().getResource( "/test.txt" ).getFile() );
        List<String> lines = null;
        try {
            lines = Files.readLines( file, Charsets.UTF_8 );
        } catch( IOException e ) {
            e.printStackTrace();
        }
        System.out.println( lines );
        assert lines != null;
        System.out.println( lines.size() );

    }
}
