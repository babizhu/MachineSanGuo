package experiment.calc;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 13-11-29
 * Time: 下午9:38
 */
public class Calc {

    private static final String ACCOUNT_FILE = "JX.xls";
    private static final int SHEET_NUMBER = 2;
    Sheet sheet;


    public Calc() {
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

    private String mapping(int i) {
        switch (i) {
            case 1:
                return "一月";
            case 2:
                return "二月";
            case 3:
                return "三月";
            case 4:
                return "四月";
            case 5:
                return "五月";
            case 6:
                return "六月";
            case 7:
                return "七月";
            case 8:
                return "八月";
            case 9:
                return "九月";
            case 10:
                return "十月";
            case 11:
                return "十一月";
            case 12:
                return "十二月";
        }
        return null;
    }

    private String getValue(Cell c) {
        if (c == null) {
            return "";
        }
        String content = c.toString();
        return content == null ? "" : content;
    }

    private List<Row> getRowByMonth(int month) {
        List<Row> list = new ArrayList<Row>();
        for (Row row : sheet) {
            Cell c = row.getCell(0);
            String date = getValue(c);
            if (date != null && date.contains(mapping(month))) {
                list.add(row);
            }
        }
        return list;
    }

    public int calcPay(String key, int month) {
        int payAmount = 0;
        List<Row> payList = getRowByMonth(month);
        for (Row row : payList) {
            Cell c = row.getCell(3);
            if (getValue(c).contains(key)) {
                Cell payCell = row.getCell(2);
                int pay = (int) payCell.getNumericCellValue();
                payAmount += pay;
                System.out.println(getValue(row.getCell(0)) + "   " + pay + "\t\t" + getValue(c));
            }
        }
        System.out.println(month + "月总共支出[" + key + "]" + payAmount + "元。");
        return payAmount;
    }

    public static void main(String[] args) {
        //System.out.println( args.length );
        if (args.length < 2) {
            System.out.println("用法: java -jar Tools-1.0-SNAPSHOT.jar 月份 关键字");
            return;
        }
        int month = Integer.parseInt(args[0]);
        String key = args[1];
        new Calc().calcPay(key, month);
    }

}
