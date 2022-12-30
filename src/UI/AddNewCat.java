package UI;

import data.*;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.jar.JarEntry;

public class AddNewCat extends JFrame{

    /**
     * 实现新的猫的记录插入
     * 包含：名字，品质，颜色，特征，食物偏好，常出现的位置
     */
    AddNewCat() throws SQLException {
        // 设置页面大小
        this.setPreferredSize(new Dimension(400, 600));
        // 设置默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 限定窗口位置
        this.setLocationRelativeTo(null);

        /*
            标题box
         */
        Box titleBox = Box.createHorizontalBox();
        JLabel title = new JLabel("请输入或选择对应的猫咪信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("黑体", Font.PLAIN, 24));
        titleBox.add(Box.createHorizontalStrut(15));
        titleBox.add(title);
        titleBox.add(Box.createHorizontalStrut(15));


        /*
            猫咪姓名box
         */
        Box nameBox = Box.createHorizontalBox();
        JLabel nameTitle = new JLabel("猫咪姓名");
        JTextField nameField = new JFormattedTextField();
        nameField.setSize(new Dimension(200, 30));
        nameBox.add(nameTitle);
        nameBox.add(nameField);

        /*
            猫咪品种Box
         */
        Box categoryBox = Box.createHorizontalBox();
        JLabel categoryTitle = new JLabel("猫咪种类");
        // 创建下拉框
        JComboBox<String> categoryCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> categoryInfo = DBUtils.getAllInfoFromTable("category");
        for (List<String> eachCategory : categoryInfo) {
            categoryCombo.addItem(eachCategory.get(1));
        }
        categoryBox.add(categoryTitle);
        categoryBox.add(categoryCombo);

        /*
            猫咪颜色Box
         */
        Box colorBox = Box.createHorizontalBox();
        JLabel colorTitle = new JLabel("猫咪颜色");
        // 创建下拉框
        JComboBox<String> colorCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> colorInfo = DBUtils.getAllInfoFromTable("color");
        for (List<String> eachColor : colorInfo) {
            colorCombo.addItem(eachColor.get(1));
        }
        colorBox.add(colorTitle);
        colorBox.add(colorCombo);

        /*
            猫咪性格Box
         */
        Box featureBox = Box.createHorizontalBox();
        JLabel featureTitle = new JLabel("猫咪性格");
        // 创建下拉框
        JComboBox<String> featureCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> featureInfo = DBUtils.getAllInfoFromTable("feature");
        for (List<String> eachFeature : featureInfo) {
            featureCombo.addItem(eachFeature.get(1));
        }
        featureBox.add(featureTitle);
        featureBox.add(featureCombo);

        /*
            猫咪食物偏好Box
         */
        Box foodBox = Box.createHorizontalBox();
        JLabel foodTitle = new JLabel("食物偏好");
        // 创建下拉框
        JComboBox<String> foodCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> foodInfo = DBUtils.getAllInfoFromTable("food_preference");
        for (List<String> eachFood : foodInfo) {
            foodCombo.addItem(eachFood.get(1));
        }
        foodBox.add(foodTitle);
        foodBox.add(foodCombo);

        /*
            猫咪常出现位置box
         */
        Box locationBox = Box.createHorizontalBox();
        JLabel locationTitle = new JLabel("常见位置");
        // 创建下拉框
        JComboBox<String> locationCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> locationInfo = DBUtils.getAllInfoFromTable("location");
        for (List<String> eachLocation : locationInfo) {
            locationCombo.addItem(eachLocation.get(1));
        }
        locationBox.add(locationTitle);
        locationBox.add(locationCombo);

        /*
            信息确认按钮
         */
        Box buttonBox = Box.createHorizontalBox();
        JButton confirmButton = new JButton("新增");
        JButton returnButton = new JButton("返回");
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(returnButton);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(confirmButton);
        buttonBox.add(Box.createHorizontalGlue());

        /*
            总体Box
         */
        Box vbox = Box.createVerticalBox();
        vbox.add(titleBox);
        vbox.add(Box.createVerticalStrut(30));
        vbox.add(nameBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(colorBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(categoryBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(featureBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(foodBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(locationBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(buttonBox);
        vbox.add(Box.createVerticalStrut(30));

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        // 新增猫咪信息触发器
        confirmButton.addActionListener(actionEvent -> {
            // 获取用户选择的猫咪各项信息
            String name = nameField.getText();
            String category = (String) categoryCombo.getSelectedItem();
            String color = (String) colorCombo.getSelectedItem();
            String foodPreference = (String) foodCombo.getSelectedItem();
            String feature = (String) featureCombo.getSelectedItem();
            String location = (String) locationCombo.getSelectedItem();
            // 插入数据库
            try {
                if (name.isEmpty()) {
                    JOptionPane.showConfirmDialog(null,
                            "姓名栏不可为空！",
                            "提示",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    DBUtils.insertCat(name, category, color, foodPreference, feature, location);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 页面返回触发器
        returnButton.addActionListener(actionEvent -> jumpToHomePage());
    }

    public void jumpToHomePage() {
        this.dispose();
        new CatInfo();
    }

    public static void main(String[] args) {
        try {
            new AddNewCat();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
