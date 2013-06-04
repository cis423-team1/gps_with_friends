package edu.uoregon.cs;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
    private String query(String queryStatement){
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        String resString = res.toString();
        
        //close connection
        try { if (res != null) res.close(); } catch (Exception e) {};
        try { if (st != null) st.close(); } catch (Exception e) {};
        try { if (conn != null) conn.close(); } catch (Exception e) {};
        
        return resString;
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
    public Status addUser(User user, String password) {
        //create user id
        String queryStatement = "SELECT COUNT(*) FROM `User`";
        //connect to db
        Connection conn = openConnection();
        ResultSet result;
        Statement st;
        //try to get result
        try{
        st = conn.createStatement();
        result = st.executeQuery(queryStatement);
        }catch(Exception e){
            return new Status(false, "Counting the number of users failed");
        }
        //check if query failed
        if (result == null) {
            return new Status(false, "Counting the number of users failed");
        }
        //get count and set it as the new group id (ids start at zero)
        int uid;
        try {
            //get count and save to uid
            if (result.next()) {
                uid = result.getInt("Count(*)");
            } else {
                return new Status(false, "Failed to get count from user");
            }
        } catch (SQLException ex) {
            return new Status(false, "Failed to get count from user");
        }
        
        //close connection
        try { if (result != null) result.close(); } catch (Exception e) {return new Status(false, "Counting the number of users failed");};
        try { if (st != null) st.close(); } catch (Exception e) {return new Status(false, "Counting the number of users failed");};
        try { if (conn != null) conn.close(); } catch (Exception e) {return new Status(false, "Counting the number of users failed");};
        
        //insert values into database
        int res = update("INSERT INTO `User` (UID, Fname, Lname, Email, Password) VALUES"
                + " ("+uid+", '"+user.fName+"', '"+user.lName+"', '"+user.email+"', + '"+password+"')");
        
        //convert int to boolean
        if (res == 1) {
            return new Status(true, "success");
        } else {
            return new Status(false, "Failed to insert user into database");
        }
    }
    
    /*
     * returns a status detailing whether it was a success or failure along with an error message
     */
    public Status insertLocation(Location loc, int user) {
        //get and prepare values for database
        double lat = loc.latitude;
        double lon = loc.longitude;
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
        String queryStatement = "SELECT * FROM `Track_History` WHERE `UID`="+uid+" ORDER BY `Date` DESC, `Time` DESC LIMIT 1";
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return new Location (0,0,0,"execute error");
        }
        //check for failed query
        if (res == null) {
            return new Location(0,0,0,"nullres");
        }
        try {
            //get first (and only) line
            res.next();
            //get information
            String date = res.getString("Date") + " " + res.getString("Time");
            double lat = res.getBigDecimal("Location_x").doubleValue();
            double lon = res.getBigDecimal("Location_y").doubleValue();
            
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {return new Location(0,0,0,"sqlexception");};
            try { if (st != null) st.close(); } catch (Exception e) {return new Location(0,0,0,"sqlexception");};
            try { if (conn != null) conn.close(); } catch (Exception e) {return new Location(0,0,0,"sqlexception");};
            
            //return location
            return new Location(uid, lat, lon, date);
            
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
        String queryStatement = "SELECT COUNT(*) FROM `Group`";
        //connect to db
        Connection conn = openConnection();
        ResultSet result;
        Statement st;
        //try to get result
        try{
        st = conn.createStatement();
        result = st.executeQuery(queryStatement);
        }catch(Exception e){
            return new Status(false, "Counting the number of groups failed");
        }
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
        
        //close connection
        try { if (result != null) result.close(); } catch (Exception e) {return new Status(false, "Counting the number of groups failed");};
        try { if (st != null) st.close(); } catch (Exception e) {return new Status(false, "Counting the number of groups failed");};
        try { if (conn != null) conn.close(); } catch (Exception e) {return new Status(false, "Counting the number of groups failed");};
            
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
        String queryStatement = "SELECT * FROM `Group` JOIN `Group_Lists` ON `Group_Lists`.`Group_GroupID`=`Group`.`GroupID` WHERE `Group_Lists`.`UID`="+uid;
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //try to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            Group [] nullData = {new Group()};
            return nullData;
        }
        
        try {
            ArrayList<Group> groups = new ArrayList<Group>();
            //for each group get the user list and add group to groups
            while (res.next()) {
                 queryStatement = "SELECT * FROM `User` JOIN `Group_Lists` ON `Group_Lists`.`UID`=`User`.`UID` WHERE `Group_Lists`.`Group_GroupID`="+res.getInt("GroupID");
                 //execute querry and get result
                 Statement st2 = conn.createStatement();
                 ResultSet userRes = st2.executeQuery(queryStatement);
                //check for failed query
                if (userRes == null) {
                    Group [] nullData = {new Group()};
                    return nullData;
                }
                //assemble user list for group
                ArrayList<User> users = new ArrayList<User>();
                while (userRes.next()) {
                    users.add(new User(userRes.getInt("UID"), userRes.getString("Fname"), userRes.getString("Lname"), userRes.getString("Email"), getLastLocation(userRes.getInt("UID"))));
                }
                
                //close userRes
                try { if (userRes != null) userRes.close(); } catch (Exception e) {};
                try { if (st2 != null) st2.close(); } catch (Exception e) {};
                
                User [] userRay = users.toArray(new User[users.size()]);
                groups.add(new Group(userRay, res.getString("GroupName"), res.getInt("OwnerID"), res.getString("Date_Of_Creation"),res.getInt("GroupID")));
            }
            
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
            
            //return groups
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
        String queryStatement = "SELECT * FROM `User` JOIN `Group_Lists` ON `Group_Lists`.`UID`=`User`.`UID` WHERE `Group_Lists`.`Group_GroupID`="+gid;
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //try to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            User [] nullData = {new User()};
            return nullData;
        }
        //assemble user list for group
        ArrayList<User> users = new ArrayList<User>();
        try {
            while (res.next()) {
                users.add(new User(res.getInt("UID"), res.getString("Fname"), res.getString("Lname"), res.getString("Email"), getLastLocation(res.getInt("UID"))));
            }
        } catch (SQLException ex) {
            User [] nullData = {new User()};
            return nullData;
        }
        
        //close connection
        try { if (res != null) res.close(); } catch (Exception e) {return null;};
        try { if (st != null) st.close(); } catch (Exception e) {return null;};
        try { if (conn != null) conn.close(); } catch (Exception e) {return null;};
            
        //return data
        return users.toArray(new User[users.size()]);
    }
    
        public User getUser(String email) {
        String queryStatement = "SELECT * FROM `User` WHERE `Email`='"+email+"'";
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            return null;
        }
        try {
            //get first (and only) line
            res.next();
            //get information
            String fname = res.getString("Fname");
            String lname = res.getString("Lname");
            int uid = res.getInt("UID");
           
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
            
            //return location
            return new User(uid, fname, lname, email, getLastLocation(uid));
            
        } catch (SQLException ex) {
            return null;
        }
    }
        
         public Location[] getHistory(int uid, int limit) {
        String queryStatement = "SELECT * FROM `Track_History` WHERE `UID`="+uid+" ORDER BY `Date` DESC, `Time` DESC LIMIT "+limit;
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            return null;
        }
        ArrayList<Location> locationList = new ArrayList<Location>();
        try {
            //get first (and only) line
            while (res.next()){
            //get information
            String date = res.getString("Date") + " " + res.getString("Time");
            double lat = res.getBigDecimal("Location_x").doubleValue();
            double lon = res.getBigDecimal("Location_y").doubleValue();
            
            
            //return location
            locationList.add(new Location(uid, lat, lon, date));
            }
            
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
            
            return locationList.toArray(new Location[locationList.size()]);
            
        } catch (SQLException ex) {
            return null;
        }
    }
         
    public UserStatus authenticate(String email, String pass) {
        String queryStatement = "SELECT * FROM `User` WHERE `Email`='"+email+"'";
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return new UserStatus(null, false, "Incorrect email or password");
        }
        //check for failed query
        if (res == null) {
            return new UserStatus(null, false, "Incorrect email or password");
        }
        try {
            //get first (and only) line
            res.next();
            //get information
            boolean match = res.getString("Password").equals(pass);
            
            //check for matching passwords
            String message = "Incorrect email or password";
            User user = null;
            if (match) {
                message = "success";
                user = new User(res.getInt("UID"), res.getString("Fname"), res.getString("Lname"), res.getString("Email"), getLastLocation(res.getInt("UID")));
            }
            
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};

            
            //return Status of authentication
            return new UserStatus (user, match, message);
            
        } catch (SQLException ex) {
            return new UserStatus(null, false, "Incorrect email or password");
        }
    }
    
    public User getUserByID(int uid) {
        String queryStatement = "SELECT * FROM `User` WHERE `UID`='"+uid+"'";
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            return null;
        }
        try {
            //get first (and only) line
            res.next();
            //get information
            String fname = res.getString("Fname");
            String lname = res.getString("Lname");
            String email = res.getString("Email");
           
            //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
            
            //return location
            return new User(uid, fname, lname, email, getLastLocation(uid));
            
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Group getGroup(int gid) {
        String queryStatement = "SELECT * FROM `Group` WHERE `GroupID`='"+gid+"'";
        //connect to db
        Connection conn = openConnection();
        ResultSet res;
        Statement st;
        //attempt to get result
        try{
        st = conn.createStatement();
        res = st.executeQuery(queryStatement);
        }catch(Exception e){
            return null;
        }
        //check for failed query
        if (res == null) {
            return null;
        }
        try {
            res.next();
            queryStatement = "SELECT * FROM `User` JOIN `Group_Lists` ON `Group_Lists`.`UID`=`User`.`UID` WHERE `Group_Lists`.`Group_GroupID`="+res.getInt("GroupID");
            //execute querry and get result
            Statement st2 = conn.createStatement();
            ResultSet userRes = st2.executeQuery(queryStatement);
           //check for failed query
           if (userRes == null) {
               return null;
           }
           //assemble user list for group
           ArrayList<User> users = new ArrayList<User>();
           while (userRes.next()) {
               users.add(new User(userRes.getInt("UID"), userRes.getString("Fname"), userRes.getString("Lname"), userRes.getString("Email"), getLastLocation(userRes.getInt("UID"))));
           }
           
           //close userRes
           try { if (userRes != null) userRes.close(); } catch (Exception e) {};
           try { if (st2 != null) st2.close(); } catch (Exception e) {};
           
           String name = res.getString("GroupName");
           int owner = res.getInt("OwnerID");
           String date = res.getString("Date_Of_Creation");
           
           //close connection
            try { if (res != null) res.close(); } catch (Exception e) {};
            try { if (st != null) st.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};

           User [] userRay = users.toArray(new User[users.size()]);
           return new Group(userRay, name, owner, date,gid);
            
        } catch (SQLException ex) {
            return null;
        }
    }
}
