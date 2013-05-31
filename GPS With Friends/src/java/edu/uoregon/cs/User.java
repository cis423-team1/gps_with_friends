/*
 * Contains all fields required by the client when requesting user data
 */
package edu.uoregon.cs;

public class User {
    public int uid;
    public String fName;
    public String lName;
    public String email;
    
    public User() {
        uid = -1;      
    }
    
    public User(int u, String f, String l, String e) {
        uid = u;
        fName = f;
        lName = l;
        email = e;
    }
}
