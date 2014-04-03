package gen.db.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-21
 * Time: 下午5:14
 */
public class DataTransUtil {

    /**
     * 把数据库字段的整型数据类型更新为类属性的字符串型数据类型
     *
     * @param type 字段数据类型
     * @return 属性数据类型
     */
    public static String getTypeFromDb(int type) {

        switch (type) {
            case -7:// bit
                return "boolean";
            case -6:
                return "short";
            case -5:// BIGINT
                return "long";
            case -3:
                return "byte[]";
            case -1:
                return "String";
            case 1:
                // t = "char";
                return "byte";
            case 3:// decimal
                return "int";
            case 4:
                return "int";

            case 5:
                return "short";

            case 6:
                return "float";

            case 7:
                return "float";

            case 8:
                return "double";

            case 12:
                return "String";

            case 16:
                return "boolean";

            case 91:
                return "Date";

            case 93:
                return "Date";

            default:
                System.out.println("类型:" + type);
                return type + "没有做相应的处理，请添加";

        }

    }

    /**
     * 用于pstAdd或者pstUpdate
     *
     * @param type
     * @return
     */
    public static String getTypeFromDb1(int type) {

        switch (type) {
            case -7:// bit
                return "boolean";
            case -6:
                return "Short";
            case -5:// BIGINT
                return "Long";
            case -3:
                return "byte[]";
            case -1:
                return "String";
            case 1:
                // t = "char";
                return "Byte";
            case 3:// decimal
                return "Int";
            case 4:
                return "Int";

            case 5:
                return "short";

            case 6:
                return "float";

            case 7:
                return "float";

            case 8:
                return "double";

            case 12:
                return "String";

            case 16:
                return "boolean";

            case 91:
                return "Date";

            case 93:
                return "Date";

            default:
                System.out.println("类型:" + type);
                return type + "没有做相应的处理，请添加";

        }

    }


}
