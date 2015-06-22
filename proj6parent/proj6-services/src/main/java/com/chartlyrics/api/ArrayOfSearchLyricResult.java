
package com.chartlyrics.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSearchLyricResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSearchLyricResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchLyricResult" type="{http://api.chartlyrics.com/}SearchLyricResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSearchLyricResult", propOrder = {
    "searchLyricResult"
})
public class ArrayOfSearchLyricResult {

    @XmlElement(name = "SearchLyricResult", nillable = true)
    protected List<SearchLyricResult> searchLyricResult;

    /**
     * Gets the value of the searchLyricResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchLyricResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchLyricResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchLyricResult }
     * 
     * 
     */
    public List<SearchLyricResult> getSearchLyricResult() {
        if (searchLyricResult == null) {
            searchLyricResult = new ArrayList<SearchLyricResult>();
        }
        return this.searchLyricResult;
    }

}
