
package edu.uoregon.cs.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setLocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setLocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setLocation", propOrder = {
    "uid",
    "latitude",
    "longitude"
})
public class SetLocation {

    protected int uid;
    protected long latitude;
    protected long longitude;

    /**
     * Gets the value of the uid property.
     * 
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     */
    public void setUid(int value) {
        this.uid = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     */
    public long getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(long value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     */
    public long getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     */
    public void setLongitude(long value) {
        this.longitude = value;
    }

}
