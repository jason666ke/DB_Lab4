package feed;

import data.Cat;
import data.DBUtils;
import data.FoodPreference;
import data.Location;
import user.User;

import java.sql.SQLException;

public class Feed {

    public Feed(User user, Cat cat, String date, Location location, FoodPreference food) throws SQLException {
        this.feedID = DBUtils.getNextPK("feed");
        this.user = user;
        this.cat = cat;
        this.date = date;
        this.location = location;
        this.food = food;
    }

    private final int feedID;
    private final User user;
    private final String date;
    private final Location location;
    private final FoodPreference food;
    private final Cat cat;

    public FoodPreference getFood() {
        return food;
    }

    public Location getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public int getFeedID() {
        return feedID;
    }

    public Cat getCat() {
        return cat;
    }
}
