package UI;

import data.Cat;
import data.DBUtils;
import data.FoodPreference;
import data.Location;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Feed extends JFrame {

    Feed() throws SQLException {
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
        JLabel title = new JLabel("投喂登记");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("黑体", Font.PLAIN, 24));
        titleBox.add(Box.createHorizontalStrut(15));
        titleBox.add(title);
        titleBox.add(Box.createHorizontalStrut(15));

        /*
            选择猫咪Box
         */
        Box catBox = Box.createHorizontalBox();
        JLabel catTitle = new JLabel("选择猫咪");
        // 创建下拉框
        JComboBox<String> catCombo = new JComboBox<>();
        // 绑定下拉框选项
        List<List<String>> catInfo = DBUtils.getAllInfoFromTable("cat");
        for (List<String> eachCat : catInfo) {
            catCombo.addItem(eachCat.get(6));
        }
        catBox.add(catTitle);
        catBox.add(catCombo);

        /*
            猫咪常出现位置box
         */
        Box locationBox = Box.createHorizontalBox();
        JLabel locationTitle = new JLabel("投喂地点");
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
            猫咪食物偏好Box
         */
        Box foodBox = Box.createHorizontalBox();
        JLabel foodTitle = new JLabel("投喂物品");
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
            输入时间box
         */
        Box timeBox = Box.createHorizontalBox();
        JLabel timeTitle = new JLabel("输入时间");
        JTextField timeField = new JFormattedTextField();
        timeField.setSize(new Dimension(200, 30));
        timeBox.add(timeTitle);
        timeBox.add(timeField);

        /*
            按钮Box
         */
        Box buttonBox = Box.createHorizontalBox();
        JButton returnButton = new JButton("返回");
        JButton addButton = new JButton("新增");
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(returnButton);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalGlue());

        /*
            提示Box
         */
        Box tipsBox = Box.createHorizontalBox();
        JLabel tips = new JLabel("时间请按照以下格式输入：YYYY-MM-DD hh:mm:ss");
        tipsBox.add(tips);

        /*
            总体Box
         */
        Box vbox = Box.createVerticalBox();
        vbox.add(titleBox);
        vbox.add(Box.createVerticalStrut(100));
        vbox.add(catBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(locationBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(foodBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(timeBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(buttonBox);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(tipsBox);
        vbox.add(Box.createVerticalStrut(100));

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        // 新增投喂记录触发器
        addButton.addActionListener(actionEvent -> {
            // 用户选择的信息
            String name = (String) catCombo.getSelectedItem();
            String location = (String) locationCombo.getSelectedItem();
            String time = timeField.getText();
            String food = (String) foodCombo.getSelectedItem();
            // 找到对应的cat
            int catId = -1;
            for (List<String> eachCat : catInfo) {
                if (eachCat.get(6).equals(name)) {
                    catId = Integer.parseInt(eachCat.get(0));
                }
            }
            try {
                DBUtils.insertFeed(
                        Register.getCurUser(),
                        new Cat(catId, name),
                        time,
                        Location.getInstanceByName(location),
                        FoodPreference.getInstanceByName(food)
                        );
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

    public static void main(String[] args) throws SQLException {
        new Feed();
    }
}
