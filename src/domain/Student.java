package domain;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Student {

    private int id;
    private String fname, lname, address, department;

    public Student(int id, String fname, String lname, String address, String department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
