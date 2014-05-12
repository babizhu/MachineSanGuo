package experiment.calc;

import com.google.common.collect.Sets;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

/**
 * user         LIUKUN
 * time         2014/5/8 0008 21:46
 *
 * 驾校帐目统计
 */

public class Calc1{

    private static final String ACCOUNT_FILE = "e:\\JX.xls";
    private static final int SHEET_NUMBER = 5;
    /**
     * 支出项目所在的列
     */
    private static final int KEY_INDEX = 3;
    private static final int PAYMENT_INDEX = 2;
    Sheet sheet;


    public Calc1() {
        openFile(ACCOUNT_FILE);
    }


    private void openFile(String excelFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
            this.sheet = new HSSFWorkbook(fis).getSheetAt(SHEET_NUMBER);//获取总帐

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算所有支出项目的和
     * @return
     */
    void sum(){
        float allPayment = 0;

        System.out.println( "支出项目                总金额" );
        for( String key : buildKey() ) {
            float payment =  sumByKey( key );
            System.out.print( key );
            printblank( 20 - key.length() );
            System.out.println( "￥" + payment );
            allPayment += payment;
        }
        System.out.println("_______________________________");
        System.out.println( "本月总支出              ￥" + allPayment );
    }

    void printblank( int size ){
        for( int i = 0; i < size; i++ ) {

            System.out.print(" ");
        }
    }


    /**
     * 计算单个条目的和，例如计算燃料费的支出之和
     * @param key
     * @return
     */
    private float sumByKey( String key ){
        float payAmount = 0;
        for (Row row : sheet) {
            String content = row.getCell( KEY_INDEX ).toString();
            if( content == null ){
                continue;
            }
            if( content.equals( key ) ){
                Cell payCell = row.getCell( PAYMENT_INDEX );
                float pay = (float) payCell.getNumericCellValue();
                payAmount += pay;
            }
        }
        return payAmount;
    }



    /**
     * 生成这个月所有的支出项目Set
     *
     * @return
     */
    private Set<String> buildKey(){
        Set<String> key = Sets.newHashSet();
        for (Row row : sheet) {
            String content = row.getCell( KEY_INDEX ).toString();
            if( content != null ){
                key.add( content );
            }
        }
        return key;
    }

    public static void main( String[] args ){
        new Calc1().sum();
    }
}
