import java.sql.*;

 class Singleton {

    private static Connection connection = null;

    private Singleton() {
    }

    public static Connection  getConnection()throws SQLException {
                if(connection==null) {


                    connection = DriverManager.getConnection("jdbc:mysql://localhost/shopdb?serverTimezone=GMT&useSSL=false", "user", "pass");



                }
        return connection;
    }







    /**
     * Static method that returns the instance for the singleton
     *
     * @return {Connection} connection
     **/


}