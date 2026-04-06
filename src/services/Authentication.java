package services;
import Database.Queries;

public class Authentication {
    public static boolean loginchecker(String username,String password,String role){
        if(username == null || password == null){
            return false;
        }
        return Queries.checkLogin(username,password,role);
    }
}
