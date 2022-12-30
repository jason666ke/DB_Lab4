package UI;

import data.DBUtils;
import data.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class LocationRecord extends JFrame {

    LocationRecord() throws SQLException {
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
        JLabel title = new JLabel("位置打卡");
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
        java.util.List<java.util.List<String>> catInfo = DBUtils.getAllInfoFromTable("cat");
        for (List<String> eachCat : catInfo) {
            catCombo.addItem(eachCat.get(6));
        }
        catBox.add(catTitle);
        catBox.add(catCombo);

        /*
            猫咪常出现位置box
         */
        Box locationBox = Box.createHorizontalBox();
        JLabel locationTitle = new JLabel("位置打卡");
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
            按钮Box
         */
        Box buttonBox = Box.createHorizontalBox();
        JButton returnButton = new JButton("返回");
        JButton addButton = new JButton("打卡");
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(returnButton);
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalGlue());

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
        vbox.add(buttonBox);
        vbox.add(Box.createVerticalStrut(100));

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        // 位置打卡触发器
        addButton.addActionListener(actionEvent -> {
            String name = (String) catCombo.getSelectedItem();
            String locationName = (String) locationCombo.getSelectedItem();
            Location location = null;
            // 找到对应的猫咪Id
            int catId = -1;
            for (List<String> eachCat : catInfo) {
                if (eachCat.get(6).equals(name)) {
                    catId = Integer.parseInt(eachCat.get(0));
                }
            }
            try {
                location = Location.getInstanceByName(locationName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 更新地点
            assert location != null;
            DBUtils.updateCatLocation(catId, location);
        });

        returnButton.addActionListener(actionEvent -> jumpToHomePage());
    }

    public void jumpToHomePage() {
        this.dispose();
        new CatInfo();
    }

    public static void main(String[] args) throws SQLException {
        new LocationRecord();
    }

    private void actionPerformed(ActionEvent actionEvent) {
        jumpToHomePage();
    }
}
