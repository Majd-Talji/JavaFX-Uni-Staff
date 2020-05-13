package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class DBConnection {

    public static Connection connection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:uni_staff.db");
            if (con != null) {
                if (getCountInDatabase(con) == 0) {
                    createTablesInDatabase(con);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static int getCountInDatabase(Connection con) throws SQLException {
        DatabaseMetaData md = con.getMetaData();
        ResultSet tables = md.getTables(null, null, "%", null);

        List<String> tableNames = new ArrayList();
        while (tables.next()) {
            tableNames.add(tables.getString(3));
        }
        return tableNames.size();
    }

    public static void createTablesInDatabase(Connection con) throws SQLException {
        String degree = "CREATE TABLE degree ( id INT NOT NULL PRIMARY KEY, m1 double, m2 double, m3 double, m4 double,  m5 double, m6 double, sum double );";
        String student = "CREATE TABLE student (id INTEGER PRIMARY KEY, fname VARCHAR(50), lname VARCHAR(50), address VARCHAR(50), department VARCHAR(50));";
        String user = "CREATE TABLE user(user_name VARCHAR(50), password VARCHAR(50), department VARCHAR(50), PRIMARY KEY(user_name));";
        
        Statement statement = con.createStatement();        

        statement.execute(degree);
        
        statement.execute(student);
        
        statement.execute(user);
        
    }

}
