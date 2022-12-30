package data;

import feed.Feed;
import user.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

public class DBUtils {
    // 连接
    private static Connection con = connectDB();
    // 执行
    private static PreparedStatement pstmt;
    // 结果
    private static ResultSet set;
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
     * @throws SQLException sql错误
     * @return PK length
     */
    public static int getNextPK(String tableName) throws SQLException {
        // 语句
        Statement ps = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //
        ResultSet rs = ps.executeQuery("select * from " + tableName);
//        rs.last();
        System.out.println("row counts: " + rs.getRow());
        int nextPK = 0;
        while (rs.next()) {
            nextPK = rs.getInt(1) + 1;
        }
        return nextPK;
    }

    /**
     * 向color表中插入数据
     * @throws SQLException sql 错误
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
     * @throws SQLException sql错误
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
     * @throws SQLException sql错误
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
//            e.printStackTrace();
        }
        statement.close();
    }

    /**
     * 向food_preference表中插入数据
     * @param foodPreference 食物偏好
     * @throws SQLException sql错误
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
     * @throws SQLException sql错误
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
     * @throws SQLException sql错误
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
    public static void insertFeed(User user, Cat cat, String date, Location location, FoodPreference foodPreference) throws SQLException {
        Statement statement = con.createStatement();
        Feed feed = new Feed(user, cat, date, location, foodPreference);
        // 构造sql语句
        String feedInfo =
                "("
                        + feed.getFeedID() + ","
                        + cat.getId() + ","
                        + user.getUserID() + ","
                        + "\"" + date + "\"" + ","
                        + "\"" + location.getName() + "\"" + ","
                        + "\"" + foodPreference.getName() + "\"" +
                ")";
        String sql = "insert into feed values%s".formatted(feedInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.printf("feed record %s already exist!", feed.getFeedID());
            e.printStackTrace();
        }
        statement.close();
    }

    /**
     * 向cat表中插入数据
     * @param name 猫咪名字
     * @param category 猫咪种类
     * @param color 猫咪颜色
     * @param food 食物偏好
     * @param feature 猫咪特征
     * @param location 常见位置
     * @throws Exception sql错误
     */
    public static void insertCat(String name, String category, String color, String food, String feature, String location) throws Exception {
        Statement statement = con.createStatement();
        Cat newCat = new Cat(name);
        String catInfo =
                "("
                        + newCat.getId() + ","
                        + Colors.getIdByName(color) + ","
                        + FoodPreference.getIdByName(food) + ","
                        + Categorys.getIdByName(category) + ","
                        + Features.getIdByName(feature) + ","
                        + Location.getIdByName(location) + ","
                        + "\"" + newCat.getName() + "\""
                        + ")";
        String sql = "insert into cat values%s".formatted(catInfo);
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.close();
    }

    /**
     * 查询数据库中存在的所有用户信息
     * @return 存放所有用户信息的List
     * @throws SQLException sql错误
     */
    public static List<Tuple.Quartet<Integer, String, String, String>> getAllUserInfo() throws SQLException {
        // 用户信息
        List<Tuple.Quartet<Integer, String, String, String>> userList = new LinkedList<>();
        String sql = "select * from user";
        ResultSet set = null;
        PreparedStatement pstmt;
        // 查询数据库
        try {
            pstmt = con.prepareStatement(sql);
            set = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 获取列数
        assert set != null;
        ResultSetMetaData rsmd = set.getMetaData();
        int colNum = rsmd.getColumnCount();
        // 获取每一行数据
        while (set.next()) {
            int id = set.getInt(1);
            String name = set.getString(2);
            String account = set.getString(3);
            String password = set.getString(4);
            userList.add(new Tuple.Quartet<>(id, name, account, password));
        }
        return userList;
    }

    /**
     * 判断当前账号是否存在并且密码输入正确
     * @param account 用户账号
     * @param password 用户密码
     * @return 密码是否正确
     * @throws SQLException 该账号不存在
     */
    public static boolean checkUserPassword(String account, String password) throws Exception {
        List<Tuple.Quartet<Integer, String, String, String>> userList = getAllUserInfo();
        for (Tuple.Quartet<Integer, String, String, String> user : userList) {
            // 存在该账号则比对密码
            if (account.equals(user.getLeftElement())) {
                return password.equals(user.getRightElement());
            }
        }
        throw new SQLException("this user is not exist!");
    }

    public static List<List<String>> getAllInfoFromTable(String tableName) throws SQLException {
        String sql = "select * from %s".formatted(tableName);
        // 执行查询语句
        try {
            assert con != null;
            pstmt = con.prepareStatement(sql);
            set = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 从表中获取的信息
        List<List<String>> elementInfo = new LinkedList<>();
        // 获取列数
        assert set != null;
        ResultSetMetaData rsmd = set.getMetaData();
        int colNum = rsmd.getColumnCount();
        // 获取每一行数据
        int count;
        List<String> eachElementInfo = new LinkedList<>();
        while (set.next()) {
            count = 0;
            while (count <= colNum) {
                count++;
                // 读取完一整行后刷新
                if (count == (colNum + 1)) {
                    // 将获取的数据写入外层List
                    elementInfo.add(eachElementInfo);
                    // 更新内层List
                    eachElementInfo = new LinkedList<>();
                } else {
                    // 数据写入
                    eachElementInfo.add(set.getString(count));
                }
            }
        }
        return elementInfo;
    }

    /**
     * 时间格式转换
     * @param time 时间
     * @return 符合标准格式的时间
     */
    public static LocalDateTime dateFormatTransform(String time) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, formatter);
    }

    public static void updateCatLocation(int Id, Location location) {
        String sql = "update cat set location_id = %d where cat_id = %d".formatted(location.getId(), Id);
        // 执行查询语句
        try {
            assert con != null;
            pstmt = con.prepareStatement(sql);
            System.out.println(sql);
            pstmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        connectDB();
        getNextPK("user");
    }
}
