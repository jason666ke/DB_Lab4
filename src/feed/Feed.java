package feed;

import data.DBUtils;
import data.FoodPreference;
import data.Location;
import jdk.jshell.execution.LoaderDelegate;
import user.User;

import java.sql.SQLException;
import java.util.Date;

public class Feed {

    public Feed(User user, Date date, Location location, FoodPreference food) throws SQLException {
        this.feedID = DBUtils.getNextPK("feed");
        this.user = user;
        this.date = date;
        this.location = location;
        this.food = food;
    }

    private final int feedID;
    private final User user;
    private final Date date;
    private final Location location;
    private final FoodPreference food;

    public FoodPreference getFood() {
        return food;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public int getFeedID() {
        return feedID;
    }
}
