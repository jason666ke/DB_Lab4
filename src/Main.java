import UI.Register;
import data.Data_Initialization;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        // 数据准备
        Data_Initialization.dataInitial();
        // 注册页面
        new Register();
    }


}
