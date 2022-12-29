package user;

import data.DBUtils;

import java.sql.SQLException;

public class User {

    public User(String name, String account, String password) throws SQLException {
        this.userID = DBUtils.getNextPK("user");
        this.name = name;
        this.account = account;
        this.password = password;
    }

    /**
     * 用于记录当前登录的用户所使用
     * @param id 数据库中存放的id
     * @param name 用户姓名
     * @param account 用户账号
     * @param password 用户密码
     * @throws SQLException sql错误
     */
    public User(int id, String name, String account, String password) throws SQLException {
        this.userID = id;
        this.name = name;
        this.account = account;
        this.password = password;
    }

    public void setUser(String name, String account, String password) throws SQLException {
        User user = new User(name, account, password);
        DBUtils.insertUser(user);
    }

    private final int userID;
    private String name;
    private String account;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }
}
