package data;

import feed.Feed;
import user.User;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DBUtils {
    // 连接
    private static Connection con = connectDB();

    /**
     * 连接数据库
     */
    public static Connection connectDB() {
        try {
            // 注册JDBC驱动程序
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            // url
            String url = "jdbc:mysql://localhost:3306/cat_feeding_system";
            // 用户名
            String user = "root";
            // 密码
            String password = "81638252a@";
            // 建立链接
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("connect success");
                return con;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("no driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("failed");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询指定表的主键列的长度并返回下一主键值
     * @throws SQLException
     * @return PK length
     */
    public static int getNextPK(String tableName) throws SQLException {
        // 语句
        Statement ps = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //
        ResultSet rs = ps.executeQuery("select * from " + tableName);
        rs.last();
        System.out.println("row counts: " + rs.getRow());
        return rs.getRow();
    }

    /**
     * 向color表中插入数据
     * @throws SQLException
     */
    public static void insertColor(Colors color) throws SQLException {
        Statement statement = con.createStatement();
        // 生成主键信息
//        int id = getNextPK("color");
        // 构造sql语句
        String color_info = "(" + color.getId() + ',' + "\"" + color.getName() + "\"" + ")";
        String sql = "insert into color (color_id, color_name) values%s".formatted(color_info);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!%n", color.getName());
        } finally {
            statement.close();
        }
    }

    /**
     * 向category表中插入数据
     * @throws SQLException
     */
    public static void insertCategory(Categorys category) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(category.getName());
        // 获取主键
//        int id = getNextPK("category");
        // 构造sql语句
        String category_info =
                "("
                + category.getId() + ','
                + "\"" + category.getName() + "\"" + ","
                + "\"" + category.getDescription() + "\""
                + ")";
        String sql = "insert into category values%s".formatted(category_info);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!", category.getName());
        }
        statement.close();
    }

    /**
     * 向feature表中插入元素
     * @param feature 猫的特征
     * @throws SQLException
     */
    public static void insertFeature(Features feature) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(feature.getName());
        // 获取主键
//        int id = getNextPK("feature");
        // 构造sql语句
        String featureInfo =
                "("
                        + feature.getId() + ','
                        + "\"" + feature.getName() + "\"" + ","
                        + "\"" + feature.getDescription() + "\""
                        + ")";
        String sql = "insert into feature values%s".formatted(featureInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!", feature.getName());
            e.printStackTrace();
        }
        statement.close();
    }

    /**
     * 向food_preference表中插入数据
     * @param foodPreference
     * @throws SQLException
     */
    public static void insertFoodPreference(FoodPreference foodPreference) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(foodPreference.getName());
        // 获取主键
//        int id = getNextPK("food_preference");
        // 构造sql语句
        String foodPreferenceInfo =
                "("
                        + foodPreference.getId() + ','
                        + "\"" + foodPreference.getName() + "\"" + ","
                        + "\"" + foodPreference.getDescription() + "\""
                        + ")";
        String sql = "insert into food_preference values%s".formatted(foodPreferenceInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!", foodPreference.getName());
        }
        statement.close();
    }

    /**
     * 向user表中插入信息
     * @param user 用户信息
     * @throws SQLException
     */
    public static void insertUser(User user) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(user.getName());
        // 获取主键
//        int id = getNextPK("user");
        // 构造sql语句
        String userInfo =
                "("
                        + user.getUserID() + ','
                        + "\"" + user.getName() + "\"" + ","
                        + "\"" + user.getAccount() + "\"" + ","
                        + "\"" + user.getPassword() + "\""
                        + ")";
        String sql = "insert into user values%s".formatted(userInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!", user.getName());
        }
        statement.close();
    }

    /**
     * 向location表中插入信息
     * @param location 猫常出现的位置
     * @throws SQLException
     */
    public static void insertLocation(Location location) throws SQLException {
        Statement statement = con.createStatement();
        System.out.println(location.getName());
        // 构造sql语句
        String locationInfo =
                "("
                        + location.getId() + ','
                        + "\"" + location.getName() + "\""
                        + ")";
        String sql = "insert into location values%s".formatted(locationInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("this %s already exist!", location.getName());
        }
        statement.close();
    }

    /**
     * 向feed表中插入信息
     * @param user 用户信息
     * @param date 喂养时间
     * @param location 喂养位置
     * @param foodPreference 喂养食物
     * @throws SQLException sql错误
     */
    public static void insertFeed(User user, Date date, Location location, FoodPreference foodPreference) throws SQLException {
        Statement statement = con.createStatement();
        Feed feed = new Feed(user, date, location, foodPreference);
        // 构造sql语句
        String feedInfo =
                "("
                        + feed.getFeedID() + feed.getUser().getName() + date
                        + location.getName() + foodPreference.getName() +
                ")";
        String sql = "insert into location values%s".formatted(feedInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("feed record %s already exist!", feed.getFeedID());
        }
        statement.close();
    }

    public static void main(String[] args) throws SQLException {
        connectDB();
    }
}
