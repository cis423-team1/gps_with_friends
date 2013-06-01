/*
 * Contains all data needed about a specific recorded location
 */
package edu.uoregon.cs;

public class Location {
    public int uid;
    public double latitude;
    public double longitude;
    public String date;
    
    public Location() {
        uid = -1;
        latitude = -1;
        longitude = -1;
    }
    public Location(int u, double lat, double lon, String d) {
        uid = u;
        latitude = lat;
        longitude = lon;
        date = d;
    }
}
