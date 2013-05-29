package edu.uoregon.cs;

import java.sql.*;

/*
 * This is an outline of a program using JDBC. Eventually code using the 
 * Hibernate (http://www.hibernate.org/) package should be used here.
 */
public class DbConnection {
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "mydb";
    private String username = "root";
    private String password = "12345";
    private String driver = "com.mysql.jdbc.Driver";
    
    public DbConnection(){
        
    }
    
    public DbConnection(String url){
        this.url = url;
    }
    
    private Connection openConnection(){
        Connection conn = null;
        try{
            Class.forName(this.driver).newInstance();
            conn = DriverManager.getConnection(this.url+this.dbName,this.username,this.password);
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    
    private void closeConnection(Connection conn){
        try{
            conn.close();
        } catch(Exception e){
            e.printStackTrace();;
        }
        return;
    }
    
    /*
     * returns null on error
     */
    public String query(String queryStatement){
        Connection conn = openConnection();
        ResultSet res = null;
        try{
        Statement st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            e.printStackTrace();
        }
        closeConnection(conn);
        return res == null ? null : res.toString();
    }
    
    /*
     * return -1 on error, 1 on success
     */
    public int insert(String insertQueryStatement){
        Connection conn = openConnection();
        int res = -1;
        try{
            Statement st = conn.createStatement();
            res = st.executeUpdate(insertQueryStatement);
        }catch(Exception e){
            e.printStackTrace();
        }
        closeConnection(conn);
        return res;
    }
}
