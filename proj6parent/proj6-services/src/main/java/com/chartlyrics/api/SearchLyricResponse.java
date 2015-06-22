
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
 *         &lt;element name="SearchLyricResult" type="{http://api.chartlyrics.com/}ArrayOfSearchLyricResult" minOccurs="0"/>
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
    "searchLyricResult"
})
@XmlRootElement(name = "SearchLyricResponse")
public class SearchLyricResponse {

    @XmlElement(name = "SearchLyricResult")
    protected ArrayOfSearchLyricResult searchLyricResult;

    /**
     * Gets the value of the searchLyricResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSearchLyricResult }
     *     
     */
    public ArrayOfSearchLyricResult getSearchLyricResult() {
        return searchLyricResult;
    }

    /**
     * Sets the value of the searchLyricResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSearchLyricResult }
     *     
     */
    public void setSearchLyricResult(ArrayOfSearchLyricResult value) {
        this.searchLyricResult = value;
    }

}
