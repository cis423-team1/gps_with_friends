/*
 * Contains all data needed about a specific recorded location
 */
package edu.uoregon.cs;

public class Location {
    public long latitude;
    public long longitude;
    public String date;
    
    public Location(long lat, long lon, String d) {
        latitude = lat;
        longitude = lon;
        date = d;
    }
}
