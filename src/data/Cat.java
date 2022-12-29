package data;

import java.sql.SQLException;

public class Cat {

    private final int id;
    private final String name;

    public Cat(String name) throws SQLException {
        this.id = DBUtils.getNextPK("cat");
        this.name = name;
    }

    public Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
