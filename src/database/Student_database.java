package database;

import domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Student_database {

    public static void inset_student(String fname, String lname, String address, String depart) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("insert into student (fname, lname, address, department) values(?,?,?,?)");) {
            p.setString(1, fname);
            p.setString(2, lname);
            p.setString(3, address);
            p.setString(4, depart);
            p.execute();
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Student get_student(int id) {
        Student student = null;
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("select * from student where id = ?");) {
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("address"), rs.getString("department"));
            }
            
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public static ObservableList<Student> get_students(String department) {
        ObservableList<Student> list = FXCollections.observableArrayList();

        try {
            Connection con = DBConnection.connection();
            PreparedStatement p = con.prepareStatement("select * from student where department = ?");
            p.setString(1, department);
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getString("address"), rs.getString("department")));
            }

            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static ObservableList<Student> get_students_and_degree() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("select * from student,degree where student.id = degree.id");) {
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getInt("sum") + "", rs.getString("department")));
            }
            
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void update_student(int id, String fname, String lname, String address, String depart) {
        try (
                Connection con = DBConnection.connection();
                PreparedStatement p = con.prepareStatement("update student set fname=?, lname=?, address=?, department=? where id=?");) {
            p.setString(1, fname);
            p.setString(2, lname);
            p.setString(3, address);
            p.setString(4, depart);
            p.setInt(5, id);
            p.execute();
            
            con.close();
            p.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
