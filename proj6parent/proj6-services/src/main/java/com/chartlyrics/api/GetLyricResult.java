
package com.chartlyrics.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetLyricResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetLyricResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackChecksum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LyricChecksum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LyricSong" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricArtist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricCovertArtUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LyricCorrectUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Lyric" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLyricResult", propOrder = {
    "trackChecksum",
    "trackId",
    "lyricChecksum",
    "lyricId",
    "lyricSong",
    "lyricArtist",
    "lyricUrl",
    "lyricCovertArtUrl",
    "lyricRank",
    "lyricCorrectUrl",
    "lyric"
})
public class GetLyricResult {

    @XmlElement(name = "TrackChecksum")
    protected String trackChecksum;
    @XmlElement(name = "TrackId")
    protected int trackId;
    @XmlElement(name = "LyricChecksum")
    protected String lyricChecksum;
    @XmlElement(name = "LyricId")
    protected int lyricId;
    @XmlElement(name = "LyricSong")
    protected String lyricSong;
    @XmlElement(name = "LyricArtist")
    protected String lyricArtist;
    @XmlElement(name = "LyricUrl")
    protected String lyricUrl;
    @XmlElement(name = "LyricCovertArtUrl")
    protected String lyricCovertArtUrl;
    @XmlElement(name = "LyricRank")
    protected int lyricRank;
    @XmlElement(name = "LyricCorrectUrl")
    protected String lyricCorrectUrl;
    @XmlElement(name = "Lyric")
    protected String lyric;

    /**
     * Gets the value of the trackChecksum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackChecksum() {
        return trackChecksum;
    }

    /**
     * Sets the value of the trackChecksum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackChecksum(String value) {
        this.trackChecksum = value;
    }

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
     * Gets the value of the lyricChecksum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricChecksum() {
        return lyricChecksum;
    }

    /**
     * Sets the value of the lyricChecksum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricChecksum(String value) {
        this.lyricChecksum = value;
    }

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
     * Gets the value of the lyricSong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricSong() {
        return lyricSong;
    }

    /**
     * Sets the value of the lyricSong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricSong(String value) {
        this.lyricSong = value;
    }

    /**
     * Gets the value of the lyricArtist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricArtist() {
        return lyricArtist;
    }

    /**
     * Sets the value of the lyricArtist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricArtist(String value) {
        this.lyricArtist = value;
    }

    /**
     * Gets the value of the lyricUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricUrl() {
        return lyricUrl;
    }

    /**
     * Sets the value of the lyricUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricUrl(String value) {
        this.lyricUrl = value;
    }

    /**
     * Gets the value of the lyricCovertArtUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricCovertArtUrl() {
        return lyricCovertArtUrl;
    }

    /**
     * Sets the value of the lyricCovertArtUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricCovertArtUrl(String value) {
        this.lyricCovertArtUrl = value;
    }

    /**
     * Gets the value of the lyricRank property.
     * 
     */
    public int getLyricRank() {
        return lyricRank;
    }

    /**
     * Sets the value of the lyricRank property.
     * 
     */
    public void setLyricRank(int value) {
        this.lyricRank = value;
    }

    /**
     * Gets the value of the lyricCorrectUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLyricCorrectUrl() {
        return lyricCorrectUrl;
    }

    /**
     * Sets the value of the lyricCorrectUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLyricCorrectUrl(String value) {
        this.lyricCorrectUrl = value;
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

}
