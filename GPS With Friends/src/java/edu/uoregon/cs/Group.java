/*
 * This will contain all needed information about a group to be sent to a client
 */
package edu.uoregon.cs;

public class Group {
    public User[] users;
    public String name;
    public int gid;
    public int owner;
    public String date_created;
    
    public Group() {
        owner = -1;
    }
    
    public Group (User[] u, String n, int o, String date,int gid) {
        users = u;
        this.gid = gid;
        name = n;
        owner = o;
        date_created = date;
    }
}
