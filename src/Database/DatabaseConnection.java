package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

public static Connection Connect() {
    Connection conn = null;

    try{
        conn= DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/DATA",
                "root",
                "Parth@123"
        );

        System.out.println("Connected to database successfully");


        Statement st = conn.createStatement();

        String users = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "username VARCHAR(50) UNIQUE,"
                + "password VARCHAR(100),"
                + "role VARCHAR(20)"
                + ")";

        st.execute(users);

        String admin = "INSERT INTO users (username,password,role) "
                + "SELECT 'admin','admin123','ADMIN' "
                + "WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='admin')";

        st.execute(admin);

       String faculty = "CREATE TABLE IF NOT EXISTS faculty ("
        + "id INT PRIMARY KEY AUTO_INCREMENT,"
        + "faculty_id VARCHAR(20) UNIQUE,"
        + "name VARCHAR(100) NOT NULL,"
        + "department VARCHAR(100),"
        + "designation VARCHAR(50),"
        + "email VARCHAR(100) UNIQUE,"
        + "phone VARCHAR(15),"
        + "qualification VARCHAR(100),"
        + "experience INT,"
        + "joining_date DATE,"
        + "password VARCHAR(100),"
        + "status VARCHAR(20) DEFAULT 'ACTIVE'"
        + ")";

        st.execute(faculty);
        
        String student = "CREATE TABLE IF NOT EXISTS students ("
        + "id INT PRIMARY KEY AUTO_INCREMENT,"
        + "student_id VARCHAR(20) UNIQUE,"
        + "name VARCHAR(100) NOT NULL,"
        + "course VARCHAR(100),"
        + "branch VARCHAR(100),"
        + "year INT,"
        + "email VARCHAR(100) UNIQUE,"
        + "phone VARCHAR(15),"
        + "address VARCHAR(255),"
        + "admission_date DATE,"
        + "password VARCHAR(100),"
        + "status VARCHAR(20) DEFAULT 'ACTIVE'"
        + ")";

        st.execute(student);

        System.out.println("Tables ready + Admin created + Faculty created + Student created");
    }
       catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

}
}

