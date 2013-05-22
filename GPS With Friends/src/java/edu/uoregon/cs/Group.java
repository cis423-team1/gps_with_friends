/*
 * This will contain all needed information about a group to be sent to a client
 */
package edu.uoregon.cs;

public class Group {
    public String[] uids;
    public String name;
    
    public Group (String[] u, String n) {
        uids = u;
        name = n;
    }
}
