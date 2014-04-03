package gen.db.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-28
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public enum AddtionType {
    PATTACK(1),;

    private int number;

    private static final Map<Integer, AddtionType> numToEnum = new HashMap<Integer, AddtionType>();

    static {
        for (AddtionType t : values()) {

            AddtionType s = numToEnum.put(t.number, t);
            if (s != null) {
                throw new RuntimeException(t.number + "重复了");
            }
        }
    }

    AddtionType(int number) {
        this.number = (byte) number;
    }

    public int toNum() {
        return number;
    }

    public static AddtionType fromNum(int n) {
        return numToEnum.get(n);
    }
}
