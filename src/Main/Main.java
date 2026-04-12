package Main;
import java.sql.Connection;
import Database.DatabaseConnection;
import Gui.LoginPage;

public class Main {

    public static void main(String[] args) {

        Connection con = DatabaseConnection.Connect();

        if(con != null){
            System.out.println("Successful mangement");
        }

        LoginPage l = new LoginPage();
        l.setVisible(true);
    }
}