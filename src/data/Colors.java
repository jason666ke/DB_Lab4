package data;

public enum Colors {
    black("黑色"),
    brown("棕色"),
    orange("橙色"),
    red("红色"),
    white("白色"),
    yellow("黄色");

    private final int id;
    private final String name;

    Colors(String name) {
        this.id = ordinal();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
