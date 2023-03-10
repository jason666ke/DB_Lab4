package UI;

import data.DBUtils;
import user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatInfo extends JFrame{

    private JLabel title;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottlePanel;
    private JTable catInfoTable;
    private DefaultTableModel tableModel;
    private JButton locationButton;
    private JButton feedButton;
    private JButton addButton;
    private final Connection conn = DBUtils.connectDB();
    private PreparedStatement pstmt;
    private ResultSet set = null;

    public CatInfo() {
        // 设置页面大小
        this.setBounds(500, 400, 600, 400);
        // 设置默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 限定窗口位置
        this.setLocationRelativeTo(null);

        /*
          第一个水平箱容器，放置页面标题
         */
        Box topBox = Box.createHorizontalBox();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("仿宋", Font.PLAIN, 30));
        topBox.add(title);

        /*
            第二个水平箱容器，放置表格
         */
        Box middleBox = Box.createHorizontalBox();
        // 设置表格属性
        catInfoTable = new JTable();
        tableModel = new DefaultTableModel();
        catInfoTable.setModel(tableModel);
        JScrollPane jsp = new JScrollPane(catInfoTable);
        middleBox.add(jsp);

        /*
            第三个水平箱容器，放置四个按钮，投喂信息，出现位置打卡，投喂登记，新增猫
         */
        Box bottomBox = Box.createHorizontalBox();
        feedButton = new JButton("投喂登记");
        locationButton = new JButton("位置打卡");
        addButton = new JButton("新增猫咪");
        JButton feedInfoButton = new JButton("投喂信息");

        // 填充水平空间
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(feedButton);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(feedInfoButton);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(locationButton);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(addButton);
        bottomBox.add(Box.createHorizontalGlue());

        /*
            顶层竖直容器放置三个水平容器
         */
        Box vbox = Box.createVerticalBox();
        vbox.add(topBox);
        vbox.add(middleBox);
        vbox.add(bottomBox);

        /*
            读取数据库数据
         */
        try {
            selectCatInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        addButton.addActionListener(actionEvent -> {
            try {
                jumpToAddNewCat();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        feedButton.addActionListener(actionEvent -> {
            try {
                jumpToFeed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        locationButton.addActionListener(actionEvent -> {
            try {
                jumpToLocationRecord();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        feedInfoButton.addActionListener(actionEvent -> jumpToFeedInfo());
    }

    private void selectCatInfo() throws SQLException {
        // 从catInfo表中查询猫咪信息（用color做测试）
        // TODO: 2022/12/22 记得将color信息改为一个view视图
        String sql = "select * from catinfo";
        // 执行查询语句
        try {
            assert conn != null;
            pstmt = conn.prepareStatement(sql);
            set = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 获取表中的列数和列名作为表格组件的标题
        ResultSetMetaData rsmd = set.getMetaData();
        // 获得列数
        int colNum = rsmd.getColumnCount();
        // 将列名添加到表格模型作为表头（从1开始）
        for (int i = 1; i <= colNum; i++) {
            tableModel.addColumn(rsmd.getColumnName(i));
        }
        // 写入每一行的数据
        String[] rowData = new String[colNum];
        while (set.next()) {
            // 将查询到的每一行数据写入数组中
            for (int i = 0; i < colNum; i++) {
                rowData[i] = set.getString(i + 1);
            }
            // 写入表格
            tableModel.addRow(rowData);
        }
        // 关闭连接
        set.close();
    }


    public void jumpToAddNewCat() throws SQLException {
        this.dispose();
        new AddNewCat();
    }

    public void jumpToFeed() throws SQLException {
        this.dispose();
        new Feed();
    }

    public void jumpToLocationRecord() throws SQLException {
        this.dispose();
        new LocationRecord();
    }

    public void jumpToFeedInfo() {
        this.dispose();
        new FeedInfo();
    }

    public static void main(String[] args) {
        new CatInfo();
    }

}
