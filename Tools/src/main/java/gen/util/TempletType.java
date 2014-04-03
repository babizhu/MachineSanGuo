package gen.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-21
 * Time: 下午3:21
 */
public enum TempletType {
    DB {
        @Override
        String getDir() {
            return D.DB_TEMPLET_DIR;
        }
    },
    JAVA {
        @Override
        String getDir() {
            return D.CFG_CLASS_TEMPLET_DIR;

        }
    };

    abstract String getDir();
}