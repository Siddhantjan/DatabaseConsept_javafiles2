package com.personal.javafilestwo.database;

import java.sql.*;

public class Base {
//    static String CREATE_TABLE_EMP ="CREATE TABLE IF NOT EXISTS EMPLOYEE(Employee_id int,Name varchar(255),City varchar(255), DOB DATE,Department_id int)";
//    static String INSERT_DATA_EMP ="INSERT INTO EMPLOYEE(Employee_id,Name,City, DOB,Department_id)" +
//            "VALUES ('','Tom B. Erichsen','Skagen 21','Stavanger','4006','Norway')";
//    static String CREATE_TABLE_DEP ="CREATE TABLE IF NOT EXISTS DEPARTMENT(Department_id int,Department_name varchar(255))";
   static String QUERY="Select * from employees left outer join departments on employees.department_id=departments.department_id;";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "siddhant", "Sid@mtdt#25");
        Statement smt = connection.createStatement();
        smt.execute("use nms");
        try {
            //creations of table
//            smt.execute(CREATE_TABLE_EMP);
//            smt.execute(CREATE_TABLE_DEP);
            ResultSet res = smt.executeQuery(QUERY);
            ResultSetMetaData rsmd = res.getMetaData();
            int columns = rsmd.getColumnCount();
            int i =1;
            while(i<columns){
                System.out.println(rsmd.getColumnName(i));
                i++;
            }
            while(res.next())
                System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getString(3));
            connection.close();
            System.out.println("Created successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

