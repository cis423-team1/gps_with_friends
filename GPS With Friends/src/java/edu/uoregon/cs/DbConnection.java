package edu.uoregon.cs;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    }
    
    /*
     * returns null on error
     */
    private ResultSet query(String queryStatement){
        Connection conn = openConnection();
        ResultSet res;
        try{
        Statement st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        closeConnection(conn);
        return res;
    }
    
    /*
     * return -1 on error, 1 on success
     */
    private int update(String insertQueryStatement){
        Connection conn = openConnection();
        int res;
        try{
            Statement st = conn.createStatement();
            res = st.executeUpdate(insertQueryStatement);
        }catch(Exception e){
            return -1;
        }
        closeConnection(conn);
        return res;
    }
    /*
     * returns a status detailing whether it was a success or failure along with an error message
     */
    public Status insertLocation(Location loc, int user) {
        //get and prepare values for database
        long lat = loc.latitude;
        long lon = loc.longitude;
        String date = loc.date;
        String [] timeRay = date.split(" ");
        date = timeRay[0];
        String time = timeRay[1];
        
        //insert values into database
        int res = update("INSERT INTO `Track_History` (UID, Date, Time, Location_x, Location_y, UserList_UID) VALUES"
                + " ("+user+", '"+date+"', '"+time+"', "+lat+", "+lon+", "+user+")");
        
        //convert int to boolean
        if (res == 1) {
            return new Status(true, "success");
        } else {
            return new Status(false, "Failed to insert location into database");
        }
    }
    
    /*
     * returns a location or null if there is an error
     */
    public Location getLastLocation(int uid) {
        ResultSet res = query("SELECT * FROM `Track_History` WHERE `UID`="+uid+" ORDER BY `Date` DESC, `Time` DESC");
        //check for failed query
        if (res == null) {
            return new Location();
        }
        try {
            res.next();
            //Assemble date
            String date = res.getString("Date") + " " + res.getString("Time");
            //return location
            return new Location(uid, res.getLong("Location_x"), res.getLong("Location_y"), date);
        } catch (SQLException ex) {
            return new Location(0,0,0,"sqlexception");
        }
    }
    
    /*
     * returns a status detailing whether it was a success or failure along with an error message
     */
    public Status createGroup(String name, int owner, int[] users) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fullDate = new Date();
        String date = dateFormat.format(fullDate);
        //create group id
        ResultSet result = query("SELECT COUNT(*) FROM `Group`");
        //check if query failed
        if (result == null) {
            return new Status(false, "Counting the number of groups failed");
        }
        //get count and set it as the new group id (ids start at zero)
        int gid;
        try {
            if (result.next()) {
                gid = result.getInt("Count(*)");
            } else {
                return new Status(false, "Failed to get count from group");
            }
        } catch (SQLException ex) {
            return new Status(false, "Failed to get count from group");
        }
        //Create group
        int res = update("INSERT INTO `Group` (GroupID, GroupName, OwnerID, Date_Of_Creation, UserList_UID) VALUES"
                + " ("+gid+", '"+name+"', "+owner+", '"+date+"', "+owner+")");
        
        //return false if error
        if (res != 1) {
            return new Status(false, "Group Creation failed. Could not insert into database");
        }
        
        //add memebers to group
        for (int user : users) {
            res = update("INSERT INTO `Group_Lists` (Group_ID, UID, Group_GroupID) Values"
                    + " ("+gid+","+user+", "+gid+")");
            //return false if error
            if (res != 1) {
                return new Status(false, "Failed to insert user into database for user ID="+user);
            }
        }
        
        //return true if everything worked
        return new Status(true, "success");
    }
    
    /*
     * returns a status detailing whether it was a success or failure along with an error message
     */
    public Status addMember(int uid, int gid) {
        int res = update("INSERT INTO `Group_Lists` (Group_ID, UID, Group_GroupID) VALUES"
                + " ("+gid+", '"+uid+"', "+gid+")");
        
        //return false if error
        if (res != 1) {
            return new Status(false, "Failed to add member, DB update failed");
        }
        //return true if everything worked
        return new Status(true, "success");
    }
    
    /*
     * returns a status detailing whether it was a success or failure along with an error message
     */
    public Status removeMember(int uid, int gid) {
        int res = update("DELETE FROM `Group_Lists` WHERE `UID`="+uid+" AND `Group_GroupID`="+gid);
        
        //return false if error
        if (res != 1) {
            return new Status(false, "Failed to remove member, DB update failed");
        }
        //return true if everything worked
        return new Status(true, "success");
    }
    
    /*
     * returns null on error
     */
    public Group[] GetGroups(int uid) {
        ResultSet res = query("SELECT * FROM `Group` JOIN `Group_Lists` WHERE `Group_Lists.UID`="+uid);
        //check for failed query
        if (res == null) {
            Group [] nullData = {new Group()};
            return nullData;
        }
        
        try {
            ArrayList<Group> groups = new ArrayList<Group>();
            while (res.next()) {
                ResultSet userRes = query("SELECT * FROM `User` JOIN `Group_Lists` ON `Group_Lists`.`UID`=`User`.`UID` WHERE `Group_Lists`.`Group_GroupID`="+res.getInt("GroupID"));
                //check for failed query
                if (userRes == null) {
                    Group [] nullData = {new Group()};
            return nullData;
                }
                //assemble user list for group
                ArrayList<User> users = new ArrayList<User>();
                while (userRes.next()) {
                    users.add(new User(userRes.getInt("UID"), userRes.getString("Fname"), userRes.getString("Lname"), userRes.getString("Email")));
                }
                User [] userRay = users.toArray(new User[users.size()]);
                groups.add(new Group(userRay, res.getString("GroupName"), res.getInt("OwnerID"), res.getString("Date_Of_Creation")));
            }
            return groups.toArray(new Group[groups.size()]);
        } catch (SQLException ex) {
            Group [] nullData = {new Group()};
            return nullData;
        }
    }
    
     /*
     * returns null on error
     */
    public User[] GetMembers(int gid) {
        ResultSet res = query("SELECT * FROM `User` JOIN `Group_Lists` ON `Group_Lists`.`UID`=`User`.`UID` WHERE `Group_Lists`.`Group_GroupID`="+gid);
        //check for failed query
        if (res == null) {
            User [] nullData = {new User()};
            return nullData;
        }
        //assemble user list for group
        ArrayList<User> users = new ArrayList<User>();
        try {
            while (res.next()) {
                users.add(new User(res.getInt("UID"), res.getString("Fname"), res.getString("Lname"), res.getString("Email")));
            }
        } catch (SQLException ex) {
            User [] nullData = {new User()};
            return nullData;
        }
        return users.toArray(new User[users.size()]);
    }
}
