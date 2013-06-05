/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uoregon.cs;

/**
 *
 * @author Andrew
 */
public class UserStatus extends Status {
    
    public User user;
    
    UserStatus(User u, boolean s, String e) {
        super(s, e);
        user = u;
    }
    
}
