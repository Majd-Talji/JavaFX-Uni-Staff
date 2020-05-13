package database;

import domain.Degree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Degree_database {

    public static void inset_degree(int id, double m1, double m2, double m3, double m4, double m5, double m6) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("insert into degree values(?,?,?,?,?,?,?,?)");) {
            p.setInt(1, id);
            p.setDouble(2, m1);
            p.setDouble(3, m2);
            p.setDouble(4, m3);
            p.setDouble(5, m4);
            p.setDouble(6, m5);
            p.setDouble(7, m6);
            p.setDouble(8, m1 + m2 + m3 + m4 + m5 + m6);
            p.execute();
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Degree get_degree(int id) {
        Degree degree = null;
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("select * from degree where id=?");) {
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                degree = new Degree(rs.getInt("id"), rs.getDouble("m1"), rs.getDouble("m2"), rs.getDouble("m3"), rs.getDouble("m4"), rs.getDouble("m5"), rs.getDouble("m6"), rs.getDouble("sum"));
            }
            con.close();
            p.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return degree;
    }

    public static void update_degree(int id, double m1, double m2, double m3, double m4, double m5, double m6) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("update degree set m1=?, m2=?, m3=?, m4=?, m5=?, m6=?, sum=? where id=?");) {
            p.setDouble(1, m1);
            p.setDouble(2, m2);
            p.setDouble(3, m3);
            p.setDouble(4, m4);
            p.setDouble(5, m5);
            p.setDouble(6, m6);
            p.setDouble(7, m1 + m2 + m3 + m4 + m5 + m6);
            p.setInt(8, id);
            p.execute();
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
