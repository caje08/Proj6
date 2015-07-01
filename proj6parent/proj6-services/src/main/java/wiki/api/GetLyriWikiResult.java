package wiki.api;


	import javax.xml.bind.annotation.XmlElement;
	import javax.xml.bind.annotation.XmlRootElement;
	
	@XmlRootElement(name="LyricsResult")
	public class GetLyriWikiResult {
		
		@XmlElement(required=true,name="lyrics")
		private String lyricA;

		public String getLyric() {
			return lyricA;
		}

		public void setLyric(String lyric) {
			this.lyricA = lyric;
		}
		
		}

/*	
 * http://lyrics.wikia.com/api.php?artist=Cake&song=Dime&fmt=xml
 * 
 * XML Source Code
	<?xml version="1.0" encoding="UTF-8"?>
	<LyricsResult>
		<artist>Cake</artist>
		<song>Dime</song>
		<lyrics>In the brown shag carpet of a cheap motel
	In the dark and dusty corner by the TV shelf
	Is a small reminder of a simpler time
	When a crumpled up [...]</lyrics>
		<url>http://lyrics.wikia.com/Cake:Dime</url>
		<page_namespace>0</page_namespace>
		<page_id>34295</page_id>
		<isOnTakedownList>0</isOnTakedownList>
	</LyricsResult>*/
