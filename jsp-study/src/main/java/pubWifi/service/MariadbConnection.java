package pubWifi.service;
import java.sql.*;

public class MariadbConnection {


    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); //드라이버 로드
            //예외처리 두가지. 감당되면직접. 아니면 throw.
        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public Connection getConnect() {

        final String dbUrl = "//localhost:3306/db_pub_wifi";

        String url = "jdbc:mariadb:" + dbUrl;
	    String dbUserId = "root";
	    String dbPassword = "zerobase";
	    
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public void close(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {

        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }	

}
