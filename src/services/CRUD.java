package services;

import Database.Queries;

public class CRUD {

    public static String[] addFaculty(
            String name,
            String department,
            String designation,
            String email,
            String phone,
            String qualification,
            int experience,
            String joiningDate
    ){

        String[] credentials = new String[2];

        // generate faculty id
        String facultyId = "FAC" + System.currentTimeMillis()%100000;

        // generate password
        String password = "FAC" + (int)(Math.random()*10000);

        boolean status = Queries.addFaculty(
                facultyId,
                name,
                department,
                designation,
                email,
                phone,
                qualification,
                experience,
                joiningDate,
                password
        );

        if(status){
            credentials[0] = facultyId;
            credentials[1] = password;
        }

        return credentials;
    }
    
    public static String[] addStudent(
        String name,
        String course,
        String branch,
        int year,
        String email,
        String phone,
        String address,
        String admissionDate
){

    String[] creds = new String[2];

    String studentId = "STU" + System.currentTimeMillis()%100000;
    String password = "STU" + (int)(Math.random()*10000);

    boolean status = Queries.addStudent(
            studentId,
            name,
            course,
            branch,
            year,
            email,
            phone,
            address,
            admissionDate,
            password
    );

    if(status){
        creds[0] = studentId;
        creds[1] = password;
    }

    return creds;
 }
}