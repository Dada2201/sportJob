package database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    private static Connection connect;
    private static Properties prop;
    public static Connection getInstance() {
        if (connect == null) {
            try {
                try (InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("config.properties")) {

                    prop = new Properties();

                    if (input == null) {
                        System.out.println("Unable to find config.properties");
                        throw new Exception();
                    }

                    //load a properties file from class path, inside static method
                    prop.load(input);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                connect = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.user"),prop.getProperty("db.password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
