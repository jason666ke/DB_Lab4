package data;

import java.sql.SQLException;

public enum Location {
    canteen("食堂"),
    dormitory("住宿区"),
    teachingArea("教学区");

    private final int id;
    private final String name;


    Location(String name) {
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
