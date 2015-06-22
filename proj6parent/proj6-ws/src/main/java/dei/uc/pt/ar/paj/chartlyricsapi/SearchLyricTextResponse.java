
package dei.uc.pt.ar.paj.chartlyricsapi;

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
 *         &lt;element name="SearchLyricTextResult" type="{http://api.chartlyrics.com/}ArrayOfSearchLyricResult" minOccurs="0"/>
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
    "searchLyricTextResult"
})
@XmlRootElement(name = "SearchLyricTextResponse")
public class SearchLyricTextResponse {

    @XmlElement(name = "SearchLyricTextResult")
    protected ArrayOfSearchLyricResult searchLyricTextResult;

    /**
     * Gets the value of the searchLyricTextResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSearchLyricResult }
     *     
     */
    public ArrayOfSearchLyricResult getSearchLyricTextResult() {
        return searchLyricTextResult;
    }

    /**
     * Sets the value of the searchLyricTextResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSearchLyricResult }
     *     
     */
    public void setSearchLyricTextResult(ArrayOfSearchLyricResult value) {
        this.searchLyricTextResult = value;
    }

}