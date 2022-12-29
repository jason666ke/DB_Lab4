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

    public static int getIdByName(String name) throws Exception {
        for (Location location : Location.values()) {
            if (name.equals(location.name)) return location.id;
        }
        throw new Exception("%s don't exist in %s".formatted(name, Location.class.getName()));
    }

    public static Location getInstanceByName(String name) throws Exception {
        for (Location location : Location.values()) {
            if (name.equals(location.name)) return location;
        }
        throw new Exception("%s don't exist in %s".formatted(name, Location.class.getName()));
    }

}
