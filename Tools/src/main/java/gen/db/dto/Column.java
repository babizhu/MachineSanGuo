package gen.db.dto;

public class Column {

    private String name;
    private int type;
    private String typeName;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    private String annotation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Column) {
            Column c = (Column) o;
            return type == c.type && name.equals(c.name) && typeName.equals(c.typeName);

        } else {
            return false;
        }
    }
}
