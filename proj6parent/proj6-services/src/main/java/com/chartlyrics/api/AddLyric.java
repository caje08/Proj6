
package com.chartlyrics.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trackId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="trackCheckSum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lyric" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trackId",
    "trackCheckSum",
    "lyric",
    "email"
})
@XmlRootElement(name = "AddLyric")
public class AddLyric {

    protected int trackId;
    protected String trackCheckSum;
    protected String lyric;
    protected String email;

    /**
     * Gets the value of the trackId property.
     * 
     */
    public int getTrackId() {
        return trackId;
    }

    /**
     * Sets the value of the trackId property.
     * 
     */
    public void setTrackId(int value) {
        this.trackId = value;
    }

    /**
     * Gets the value of the trackCheckSum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackCheckSum() {
        return trackCheckSum;
    }

    /**
     * Sets the value of the trackCheckSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackCheckSum(String value) {
        this.trackCheckSum = value;
    }

    /**
     * Gets the value of the lyric property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyric() {
        return lyric;
    }

    /**
     * Sets the value of the lyric property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyric(String value) {
        this.lyric = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

}
