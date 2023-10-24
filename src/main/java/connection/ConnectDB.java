
package connection;

import java.awt.Container;
import java.io.FileInputStream;
import java.sql.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectDB{
    protected static Connection conn = null;
    private static String server;
    private static String database;
    private static String username;
    private static String password;
    private static int port;
    private static String strConnect;
    
    public ConnectDB() throws ClassNotFoundException, SQLException{
        try {
            readConfig();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(strConnect);
//            System.out.print("Ket noi DB thanh cong");
        } catch (IOException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.print("Ket noi DB that bai");
        }
    }
    
    public void connect() throws SQLException {
        conn = DriverManager.getConnection(strConnect);
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public void disconnect() throws SQLException {
        conn.close();
    }
    
    private static void readConfig() throws IOException {
//        String fileURL = "src\\connection\\DB.properties";
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(fileURL));
//        server = properties.getProperty("DB_SERVER");
//        database = properties.getProperty("DB_NAME");
//        username = properties.getProperty("DB_USERNAME");
//        password = properties.getProperty("DB_PASSWORD");
//        port = Integer.parseInt(properties.getProperty("DB_PORT"));
        strConnect = "jdbc:sqlserver://LAPTOP-LQAT4QR4\\HIEU;databasename=libManager;user=sa;password=123;encrypt=true;trustServerCertificate=true";
    }
}