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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

	/**
	 *
	 * @author 
	 */
	@Entity
	@NamedQueries({
	    @NamedQuery(name = "Lyric.findLyricByMusic&User", query = "SELECT l FROM LyricEntity l WHERE l.id = :id"),
	    @NamedQuery(name = "Lyric.existLyric", query = "SELECT COUNT(l) FROM LyricEntity l WHERE l.id = :id")
	})
	public class LyricEntity implements Serializable {

	    
		private static final long serialVersionUID = -6536368610356615266L;	
		
				
	    @NotNull
	    @Basic(optional = false)
	    @Size(min = 1)
	    private String textLyric;
	    
	    
	    @EmbeddedId
	    LyricEntityId id;
	    
	    public LyricEntityId getId() {
	        return id;
	    }

	    public void setId(LyricEntityId i) {
	        this.id = i;
	    }

	   	    public LyricEntity() {
	    }


	    public String getTextLyric() {
	        return textLyric;
	    }

	    public void setTextLyric(String textLyric) {
	        this.textLyric = textLyric;
	    } 
	    

	    @Override
	    public String toString() {
	    	return 
	     "Lyric{" + "lyric_id=" + id + ", textLyric=" + textLyric + ", music=" + id.getMusic()+ ", utilizador=" + id.getUtilizador() + '}';
	    }

	}

	