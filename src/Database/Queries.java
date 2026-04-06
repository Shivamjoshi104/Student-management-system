package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Queries {

    public static boolean checkLogin(String username, String password, String role) {

        boolean status = false;

        try {

            Connection con = DatabaseConnection.Connect();

            String query = "SELECT * FROM users WHERE username=? AND password=? AND role=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    
    public static boolean addFaculty(
        String facultyId,
        String name,
        String department,
        String designation,
        String email,
        String phone,
        String qualification,
        int experience,
        String joiningDate,
        String password
) {
    boolean status = false;
    Connection con = null;

    try {
        con = DatabaseConnection.Connect();
        con.setAutoCommit(false);

        String facultyQuery = "INSERT INTO faculty "
                + "(faculty_id,name,department,designation,email,phone,qualification,experience,joining_date,password) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps1 = con.prepareStatement(facultyQuery);
        ps1.setString(1, facultyId);
        ps1.setString(2, name);
        ps1.setString(3, department);
        ps1.setString(4, designation);
        ps1.setString(5, email);
        ps1.setString(6, phone);
        ps1.setString(7, qualification);
        ps1.setInt(8, experience);
        ps1.setString(9, joiningDate);
        ps1.setString(10, password);

        int rows1 = ps1.executeUpdate();

        String userQuery = "INSERT INTO users (username,password,role) VALUES (?,?,?)";
        PreparedStatement ps2 = con.prepareStatement(userQuery);
        ps2.setString(1, facultyId);
        ps2.setString(2, password);
        ps2.setString(3, "FACULTY");

        int rows2 = ps2.executeUpdate();

        if (rows1 > 0 && rows2 > 0) {
            con.commit();
            status = true;
        } else {
            con.rollback();
        }

    } catch (Exception e) {
        try { if (con != null) con.rollback(); } catch (Exception ignored) {}
        e.printStackTrace();
    } finally {
        try { if (con != null) con.setAutoCommit(true); } catch (Exception ignored) {}
    }

    return status;
}
    
    public static boolean addStudent(
        String studentId,
        String name,
        String course,
        String branch,
        int year,
        String email,
        String phone,
        String address,
        String admissionDate,
        String password
) {

    boolean status = false;

    try {

        Connection con = DatabaseConnection.Connect();

        String studentQuery = "INSERT INTO students "
                + "(student_id,name,course,branch,year,email,phone,address,admission_date,password) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps1 = con.prepareStatement(studentQuery);

        ps1.setString(1, studentId);
        ps1.setString(2, name);
        ps1.setString(3, course);
        ps1.setString(4, branch);
        ps1.setInt(5, year);
        ps1.setString(6, email);
        ps1.setString(7, phone);
        ps1.setString(8, address);
        ps1.setString(9, admissionDate);
        ps1.setString(10, password);

        int rows1 = ps1.executeUpdate();

        // users table insert
        String userQuery = "INSERT INTO users(username,password,role) VALUES(?,?,?)";

        PreparedStatement ps2 = con.prepareStatement(userQuery);

        ps2.setString(1, studentId);
        ps2.setString(2, password);
        ps2.setString(3, "STUDENT");

        int rows2 = ps2.executeUpdate();

        status = rows1 > 0 && rows2 > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return status;
}
    
    
}

