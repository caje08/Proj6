
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
 *         &lt;element name="lyricId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lyricCheckSum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "lyricId",
    "lyricCheckSum"
})
@XmlRootElement(name = "GetLyric")
public class GetLyric {

    protected int lyricId;
    protected String lyricCheckSum;

    /**
     * Gets the value of the lyricId property.
     * 
     */
    public int getLyricId() {
        return lyricId;
    }

    /**
     * Sets the value of the lyricId property.
     * 
     */
    public void setLyricId(int value) {
        this.lyricId = value;
    }

    /**
     * Gets the value of the lyricCheckSum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricCheckSum() {
        return lyricCheckSum;
    }

    /**
     * Sets the value of the lyricCheckSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricCheckSum(String value) {
        this.lyricCheckSum = value;
    }

}
