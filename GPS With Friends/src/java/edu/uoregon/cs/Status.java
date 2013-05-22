/*
 * This class will be passed to the client containing a boolean status or an error message
 */
package edu.uoregon.cs;

public class Status {
    public boolean success;
    public String error;
    
    public Status(boolean s, String e) {
        success = s;
        error = e;
    }
}
