/*
 * Defines web service and methods
 */
package edu.uoregon.cs;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * This is the web service that will handle all server operations with all clients
 */
@WebService(serviceName = "GPSWithFriends")
public class GPSWithFriends {

    /**
     * verifies credentials and sends the status of the authentication attempt back to the client. The user id is included in the status.
     */
    @WebMethod(operationName = "authenticate")
    public Status authenticate(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * attempts to add user to database and returns a Status object detailing if the request was successful and/or error messages.
     */
    @WebMethod(operationName = "register")
    public Status register(@WebParam(name = "email") String email, @WebParam(name = "password") String password, @WebParam(name = "fname") String fname, @WebParam(name = "lname") String lname) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * attempts to add this location with the time received to the database. Returns a status detailing error messages and/or if it was successful.
     */
    @WebMethod(operationName = "setLocation")
    public Status setLocation(@WebParam(name = "uid") String uid, @WebParam(name = "latitude") long latitude, @WebParam(name = "longitude") long longitude) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * returns the last recorded location of the specified user
     */
    @WebMethod(operationName = "getLocation")
    public Location getLocation(@WebParam(name = "uid") String uid) {
        //TODO write your implementation code here:
        return new Location(60,24,"nodate");
    }

    /**
     * attempts to create a group from the specified users. Returns the status of the request.
     */
    @WebMethod(operationName = "createGroup")
    public Status createGroup(@WebParam(name = "uids") java.lang.String[] uids) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * retrieves all groups the user is a part of and returns them in an array
     */
    @WebMethod(operationName = "getGroups")
    public Group [] getGroups(@WebParam(name = "uid") String uid) {
        //TODO write your implementation code here:
        Group [] groups = new Group[1];
        String [] members = {"no memebers"};
        groups[0] = new Group(members,"N/A");
        return groups;
    }

    /**
     * attempts to add the specified user to the specified group and returns the status of the request.
     */
    @WebMethod(operationName = "addMember")
    public Status addMember(@WebParam(name = "uid") String uid, @WebParam(name = "gid") String gid) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * attempts to remove the specified user from the specified group and returns the status of the request.
     */
    @WebMethod(operationName = "removeMember")
    public Status removeMember(@WebParam(name = "uid") String uid, @WebParam(name = "gid") String gid) {
        //TODO write your implementation code here:
        return new Status(false, "Server not set up");
    }

    /**
     * retrieves all members in the specified groups and returns them in an array
     */
    @WebMethod(operationName = "getMembers")
    public User [] getMembers(@WebParam(name = "gid") String gid) {
        //TODO write your implementation code here:
        User [] users = new User[1];
        users[0] = new User("Server not set up, no id");
        return users;
    }
}
