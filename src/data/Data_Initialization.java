package data;

import java.sql.Connection;
import java.sql.SQLException;

public class Data_Initialization {

    public void setColor() throws SQLException {
        for (Colors color : Colors.values()) {
            DBUtils.insertColor(color);
        }
    }

    public void setFeature() throws SQLException {
        for (Features feature : Features.values()) {
            DBUtils.insertFeature(feature);
        }
    }

    public void setCategory() throws SQLException {
        for (Categorys category : Categorys.values()) {
            DBUtils.insertCategory(category);
        }
    }

    public void setFoodPreference() throws SQLException {
        for (FoodPreference foodPreference : FoodPreference.values()) {
            DBUtils.insertFoodPreference(foodPreference);
        }
    }

    public void setLocation() throws SQLException {
        for (Location location: Location.values()) {
            DBUtils.insertLocation(location);
        }
    }

    public void dataInitial() throws SQLException {
        setCategory();
        setColor();
        setFeature();
        setFoodPreference();
        setLocation();
    }

    public static void main(String[] args) throws SQLException {
        Data_Initialization dataInitialization = new Data_Initialization();
        dataInitialization.dataInitial();
    }

}
