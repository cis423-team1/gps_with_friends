/*
 * Defines web service and methods
 */
package edu.uoregon.cs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * This is the web service that will handle all server operations with all clients
 */
@WebService(serviceName = "GPSwfriends")
public class GPSwfriends {

    /**
     * verifies credentials and sends the status of the authentication attempt back to the client. The user id is included in the status.
     */
    @WebMethod(operationName = "authenticate")
    public Status authenticate(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return new Status(false, "Authentication not set up");
    }

    /**
     * attempts to add user to database and returns a Status object detailing if the request was successful and/or error messages.
     */
    @WebMethod(operationName = "register")
    public Status register(@WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "fname") String fname, @WebParam(name = "lname") String lname) {
        //TODO write your implementation code here:
        return new Status(false, "Authentication not set up");
    }

    /**
     * attempts to add this location with the time received to the database. Returns a status detailing error messages and/or if it was successful.
     */
    @WebMethod(operationName = "setLocation")
    public Status setLocation(@WebParam(name = "uid") int uid, @WebParam(name = "latitude") double latitude, @WebParam(name = "longitude") double longitude) {
        DbConnection db = new DbConnection();
        //get current time and date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fullDate = new Date();
        String date = dateFormat.format(fullDate);
        //insert location into the database
        return db.insertLocation(new Location(uid, latitude, longitude, date), uid);
    }

    /**
     * returns the last recorded location of the specified user
     */
    @WebMethod(operationName = "getLocation")
    public Location getLocation(@WebParam(name = "uid") int uid) {
        DbConnection db = new DbConnection();
        return db.getLastLocation(uid);
    }

    /**
     * attempts to create a group from the specified users. Returns the status of the request.
     */
    @WebMethod(operationName = "createGroup")
    public Status createGroup(@WebParam(name = "uids") int[] uids, @WebParam(name = "owner") int owner, @WebParam(name = "name") java.lang.String name) {
        DbConnection db = new DbConnection();
        return db.createGroup(name, owner, uids);
    }

    /**
     * retrieves all groups the user is a part of and returns them in an array
     */
    @WebMethod(operationName = "getGroups")
    public Group [] getGroups(@WebParam(name = "uid") int uid) {
        DbConnection db = new DbConnection();
        return db.GetGroups(uid);
    }

    /**
     * attempts to add the specified user to the specified group and returns the status of the request.
     */
    @WebMethod(operationName = "addMember")
    public Status addMember(@WebParam(name = "uid") int uid, @WebParam(name = "gid") int gid) {
        DbConnection db = new DbConnection();
        return db.addMember(uid, gid);
    }

    /**
     * attempts to remove the specified user from the specified group and returns the status of the request.
     */
    @WebMethod(operationName = "removeMember")
    public Status removeMember(@WebParam(name = "uid") int uid, @WebParam(name = "gid") int gid) {
        DbConnection db = new DbConnection();
        return db.removeMember(uid, gid);
    }

    /**
     * retrieves all members in the specified groups and returns them in an array
     */
    @WebMethod(operationName = "getMembers")
    public User [] getMembers(@WebParam(name = "gid") int gid) {
        DbConnection db = new DbConnection();
        return db.GetMembers(gid);
    }
}
