package UI;

import data.DBUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class FeedInfo extends JFrame{

    private final Connection conn = DBUtils.connectDB();
    private PreparedStatement pstmt;
    private ResultSet set = null;

    private JTable feedInfoTable;
    private DefaultTableModel tableModel;

    FeedInfo() {
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
        JLabel title = new JLabel("投喂信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("仿宋", Font.PLAIN, 30));
        topBox.add(title);

        /*
            第二个水平箱容器，放置表格
         */
        Box middleBox = Box.createHorizontalBox();
        // 设置表格属性
        feedInfoTable = new JTable();
        tableModel = new DefaultTableModel();
        feedInfoTable.setModel(tableModel);
        JScrollPane jsp = new JScrollPane(feedInfoTable);
        middleBox.add(jsp);

        /*
            第三个水平箱容器，放置返回按钮
         */
        Box bottomBox = Box.createHorizontalBox();
        JButton returnButton = new JButton("返回");
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(returnButton);
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
            selectFeedInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 将垂直箱作为内容面板设置到窗口
        this.setContentPane(vbox);
        this.pack();
        this.setVisible(true);

        returnButton.addActionListener(actionEvent -> jumpToHomePage());
    }

    private void selectFeedInfo() throws SQLException {
        // 从catInfo表中查询猫咪信息（用color做测试）
        String sql = "select * from feedinfo";
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

    public void jumpToHomePage() {
        this.dispose();
        new CatInfo();
    }
}
