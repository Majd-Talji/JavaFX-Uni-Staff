package database;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class User_database {

    public static void insert_user(String user, String pass, String depart) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("insert into user values(?,?,?)");) {
            p.setString(1, user);
            p.setString(2, pass);
            p.setString(3, depart);
            p.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<User> get_users() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = DBConnection.connection();
            PreparedStatement p = con.prepareStatement("select * from user");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("user_name"), rs.getString("password"), rs.getString("department")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static int check_user(String user, String pass) {
        ArrayList<User> arr = get_users();
        int x = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getUser_name().equalsIgnoreCase(user)) {
                if (arr.get(i).getPassword().equalsIgnoreCase(pass)) {
                    x = 1;
                    break;
                } else {
                    x = 2;
                    break;
                }
            } else {
                x = 0;
            }
        }
        return x;
    }

    public static String get_department(String user) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("select department from user where user_name = ?");) {
            p.setString(1, user);
            ResultSet rs = p.executeQuery();

            return rs.getString("department");
        } catch (Exception e) {
        }
        return null;
    }

}
