
package com.chartlyrics.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchLyricResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchLyricResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrackChecksum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LyricChecksum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LyricId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SongUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ArtistUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Artist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Song" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SongRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchLyricResult", propOrder = {
    "trackChecksum",
    "trackId",
    "lyricChecksum",
    "lyricId",
    "songUrl",
    "artistUrl",
    "artist",
    "song",
    "songRank"
})
public class SearchLyricResult {

    @XmlElement(name = "TrackChecksum")
    protected String trackChecksum;
    @XmlElement(name = "TrackId")
    protected int trackId;
    @XmlElement(name = "LyricChecksum")
    protected String lyricChecksum;
    @XmlElement(name = "LyricId")
    protected int lyricId;
    @XmlElement(name = "SongUrl")
    protected String songUrl;
    @XmlElement(name = "ArtistUrl")
    protected String artistUrl;
    @XmlElement(name = "Artist")
    protected String artist;
    @XmlElement(name = "Song")
    protected String song;
    @XmlElement(name = "SongRank")
    protected int songRank;

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
     * Gets the value of the songUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSongUrl() {
        return songUrl;
    }

    /**
     * Sets the value of the songUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSongUrl(String value) {
        this.songUrl = value;
    }

    /**
     * Gets the value of the artistUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtistUrl() {
        return artistUrl;
    }

    /**
     * Sets the value of the artistUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtistUrl(String value) {
        this.artistUrl = value;
    }

    /**
     * Gets the value of the artist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the value of the artist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtist(String value) {
        this.artist = value;
    }

    /**
     * Gets the value of the song property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSong() {
        return song;
    }

    /**
     * Sets the value of the song property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSong(String value) {
        this.song = value;
    }

    /**
     * Gets the value of the songRank property.
     * 
     */
    public int getSongRank() {
        return songRank;
    }

    /**
     * Sets the value of the songRank property.
     * 
     */
    public void setSongRank(int value) {
        this.songRank = value;
    }

}
