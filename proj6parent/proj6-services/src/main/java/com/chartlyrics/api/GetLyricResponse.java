
package com.chartlyrics.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetLyricResult" type="{http://api.chartlyrics.com/}GetLyricResult" minOccurs="0"/>
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
    "getLyricResult"
})
@XmlRootElement(name = "GetLyricResponse")
public class GetLyricResponse {

    @XmlElement(name = "GetLyricResult")
    protected GetLyricResult getLyricResult;

    /**
     * Gets the value of the getLyricResult property.
     * 
     * @return
     *     possible object is
     *     {@link GetLyricResult }
     *     
     */
    public GetLyricResult getGetLyricResult() {
        return getLyricResult;
    }

    /**
     * Sets the value of the getLyricResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetLyricResult }
     *     
     */
    public void setGetLyricResult(GetLyricResult value) {
        this.getLyricResult = value;
    }

}
