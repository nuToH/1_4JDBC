package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Util {
    private static Connection connection;
    private static volatile Util instance;
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String userName = "root";
    private static final String passWord = "root";
    private Util() {
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, passWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {

        return connection;
    }
    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }
}



