package gen.db.dto;


import gen.db.util.BaseTypeConfig;

/**
 * 中文
 */
public class Property {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "name = " + name + "	type = " + type;
    }

    public String getPackagingType() {

        return BaseTypeConfig.parseToPackagingType(type);
    }

    public static void main(String[] args) {
        System.out.println(2);
    }

}
