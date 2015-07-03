package dei.uc.pt.ar.paj.Entities;


	import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

	/**
	 *
	 * @author 
	 */
	@Entity
	@NamedQueries({
//	    @NamedQuery(name = "Lyric.findLyricByMusic&User", query = "SELECT l FROM LyricEntity l WHERE l.id = :id"),
	    @NamedQuery(name = "Lyric.findLyricByMusic&User", query = "SELECT l FROM LyricEntity l WHERE l.id.musicID = :musicid AND l.id.userID = :userid"),
	    @NamedQuery(name = "Lyric.existLyric", query = "SELECT COUNT(l) FROM LyricEntity l WHERE l.id = :id")
	})
	public class LyricEntity implements Serializable {
	    
		private static final long serialVersionUID = -6536368610356615266L;			
				
	    @NotNull
	    @Basic(optional = false)
	    @Size(min = 1, max=10000)
	    private String textLyric;	    
	    
	    @EmbeddedId	    
	    LyricEntityId id;
	    
	    public LyricEntity() {
	    }
	    public LyricEntityId getId() {
	        return id;
	    }

	    public void setId(LyricEntityId i) {
	        this.id = i;
	    }	    
	    
	    public void setUserid(Long uid){
	    	this.id.setUserID(uid);
	    }
	    public Long getUserid(){
	    	return id.getUserID();
	    }
	    public void setMusicid(Long mid){
	    	this.id.setMusicID(mid);
	    }
	    public Long getMusicid(){
	    	return id.getMusicID();
	    }
//	    public void setUseremail(String email){
//	    	this.id.setUseremail(email);
//	    }
//	    
//	    public String getUseremail(){
//	    	return this.id.getUseremail();
//	    }
//	    
//	   	public UserEntity getUtilizador(){
//	   		return id.getUtilizador();
//	   	}
//	   	
//	   	public void setUtilizador(UserEntity u){
//	   		this.id.setUtilizador(u);
//	   	}
//	   	
//	   	public MusicEntity getMusic(){
//	   		return this.id.getMusic();
//	   	}
//	   	
//	   	public void setMusic(MusicEntity m){
//	   		this.id.setMusic(m);
//	   	}
	    public String getTextLyric() {
	        return textLyric;
	    }

	    public void setTextLyric(String textLyric) {
	        this.textLyric = textLyric;
	    } 
	    
	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((textLyric == null) ? 0 : textLyric.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LyricEntity other = (LyricEntity) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (textLyric == null) {
				if (other.textLyric != null)
					return false;
			} else if (!textLyric.equals(other.textLyric))
				return false;
			return true;
		}
		

//	    @Override
//	    public String toString() {
//	    	return 
//	     "Lyric{" + "lyric_id=" + id + ", textLyric=" + textLyric + ", music=" + id.getMusic()+ ", utilizador=" + id.getUtilizador() + '}';
//	    }
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + ((id == null) ? 0 : id.hashCode());
//			result = prime * result
//					+ ((textLyric == null) ? 0 : textLyric.hashCode());
//			return result;
//		}
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			LyricEntity other = (LyricEntity) obj;
//			if (id == null) {
//				if (other.id != null)
//					return false;
//			} else if (!id.equals(other.id))
//				return false;
//			if (textLyric == null) {
//				if (other.textLyric != null)
//					return false;
//			} else if (!textLyric.equals(other.textLyric))
//				return false;
//			return true;
//		}

	}

	
