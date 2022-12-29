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

    public static int getIdByName(String name) throws Exception {
        for (Colors color : Colors.values()) {
            if (name.equals(color.name)) return color.id;
        }
        throw new Exception("%s don't exist in %s".formatted(name, Colors.class.getName()));
    }

    public static Colors getInstanceByName(String name) throws Exception {
        for (Colors color : Colors.values()) {
            if (name.equals(color.name)) return color;
        }
        throw new Exception("%s don't exist in %s".formatted(name, Colors.class.getName()));
    }
}
