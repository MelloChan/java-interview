package base.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接
 * Created by MelloChan on 2018/1/11.
 */
public class DBConnection {
    private final static String username = "";
    private final static String pwd = "";
    private final static String url = "jdbc:mysql://localhost:3306/db?charsetEncoding=utf8";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, pwd);
    }
}
