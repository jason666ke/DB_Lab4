package UI;

import data.DBUtils;
import data.Tuple;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Register extends JFrame {
    private JLabel registerTitle;
    private JPanel titlePanel;
    private JTextField accountField;
    private JPanel accountPanel;
    private JLabel accountTitle;
    private JPasswordField passwordField;
    private JLabel passwordTitle;
    private JPanel passwordPanel;
    // 注册按钮
    private JButton logIn;
    // 登录按钮
    private JButton logUp;

    // 用户登录标记变量
    private boolean userLogUp = false;
    // 当前登录用户
    private static User curUser = null;

    public Register() {
        // 设置页面大小
//        this.setBounds(500, 400, 600, 700);
        this.setPreferredSize(new Dimension(400, 600));
        // 设置默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*
            topBox：放置页面标题
         */
        Box topBox = Box.createHorizontalBox();
        registerTitle.setText("校园猫管理系统");
        registerTitle.setFont(new Font("仿宋", Font.PLAIN, 44));
        topBox.add(registerTitle);

        /*
            middleBox: 放置账号密码输入框
         */
        Box middleBox = Box.createVerticalBox();
        middleBox.add(Box.createVerticalGlue());
        // 账户Box
        Box accountBox = Box.createHorizontalBox();
        accountTitle.setText("账号");
        accountBox.add(accountTitle);
        accountBox.add(accountField);
        // 填充间隙
        middleBox.add(Box.createVerticalGlue());
        // 密码Box
        Box passwordBox = Box.createHorizontalBox();
        passwordTitle.setText("密码");
        passwordBox.add(passwordTitle);
        passwordBox.add(passwordField);
        middleBox.add(Box.createVerticalGlue());
        // 将两个Box放入middleBox中
        middleBox.add(accountBox);
        middleBox.add(Box.createVerticalStrut(50));
        middleBox.add(passwordBox);

        /*
            bottomBox: 放置登录和注册按钮
         */
        Box bottomBox = Box.createHorizontalBox();
        // 注册
        bottomBox.add(logIn);
        // 插入间隙
        bottomBox.add(Box.createHorizontalStrut(60));
        // 登录
        bottomBox.add(logUp);

        /*
            顶层Box: 放置主框架
         */

        Box vbox = Box.createVerticalBox();
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(topBox);
        vbox.add(Box.createVerticalStrut(100));
        vbox.add(middleBox);
        vbox.add(Box.createVerticalStrut(100));
        vbox.add(bottomBox);
        vbox.add(Box.createVerticalStrut(50));

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        // 用户登录触发器
        logUp.addActionListener(actionEvent -> {
            try {
                userLogUp = userLogUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userLogUp) {
                try {
                    jumpToCatInfo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showConfirmDialog(null,
                        "密码错误请重新输入",
                        "警告",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    /**
     * 用户登录函数
     */
    private boolean userLogUp() throws Exception {
        // 获取账号密码
        String account = accountField.getText();
        String password = String.valueOf(passwordField.getPassword());
        // 查询数据库中是否有此用户存在
        boolean isExist;
        try {
            DBUtils.checkUserPassword(account, password);
        } catch (Exception e) {
            // 提示用户需要先注册账号再登录
            int userOption = JOptionPane.showConfirmDialog(null,
                    "当前账号尚未注册，需要注册该账号吗？",
                    "提示",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (userOption == JOptionPane.YES_OPTION)
                userLogIn(account, password);
        }
        // 再次判断当前用户是否存在
        isExist = DBUtils.checkUserPassword(account, password);
        return isExist;
    }

    /**
     * 用户注册函数
     */
    private void userLogIn(String account, String password) {
        // 提示输入用户姓名
        String name = JOptionPane.showInputDialog(null,
                "请输入用户名字",
                "输入",
                JOptionPane.WARNING_MESSAGE);
        try {
            DBUtils.insertUser(new User(name, account, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 页面跳转函数
     */
    private void jumpToCatInfo() throws SQLException {
        this.dispose();
        String account = accountField.getText();
        List<Tuple.Quartet<Integer, String, String, String>> userList = DBUtils.getAllUserInfo();
        for (Tuple.Quartet<Integer, String, String, String> user : userList) {
            // 找到该用户对应的账号
            if (account.equals(user.getLeftElement())) {
                curUser = new User(user.getId(), user.getName(), user.getLeftElement(), user.getRightElement());
            }
        }
        new CatInfo();
    }

    public static void main(String[] args) {
        new Register();
    }

    public static User getCurUser() {
        return curUser;
    }
}
