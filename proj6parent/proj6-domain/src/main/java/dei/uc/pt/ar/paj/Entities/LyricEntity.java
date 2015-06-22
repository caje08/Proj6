package dei.uc.pt.ar.paj.Entities;


	import java.io.Serializable;
	import javax.persistence.Basic;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.ManyToOne;
	import javax.persistence.NamedQueries;
	import javax.persistence.NamedQuery;
	import javax.validation.constraints.NotNull;
	import javax.validation.constraints.Size;

	/**
	 *
	 * @author 
	 */
	@Entity
	@NamedQueries({
	    @NamedQuery(name = "Lyric.findLyricByMusic&User", query = "SELECT l FROM LyricEntity l WHERE l.music = :music and l.utilizador = :utilizador"),
	    @NamedQuery(name = "Lyric.existLyric", query = "SELECT COUNT(l) FROM LyricEntity l WHERE l.music = :music and l.utilizador = :utilizador")
	})
	public class LyricEntity implements Serializable {

	    
		private static final long serialVersionUID = -6536368610356615266L;

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long lyric_id;

	    @NotNull
	    @Basic(optional = false)
	    @Size(min = 1)
	    @Column(columnDefinition = "LONGTEXT", nullable = false)
	    private String textLyric;

	    @ManyToOne
	    private MusicEntity music;

	    @ManyToOne
	    private UserEntity utilizador;

	    public LyricEntity() {
	    }

	    public Long getLyric_id() {
	        return lyric_id;
	    }

	    public void setLyric_id(Long lyric_id) {
	        this.lyric_id = lyric_id;
	    }

	    public String getTextLyric() {
	        return textLyric;
	    }

	    public void setTextLyric(String textLyric) {
	        this.textLyric = textLyric;
	    }

	    public MusicEntity getMusic() {
	        return music;
	    }

	    public void setMusic(MusicEntity music) {
	        this.music = music;
	    }

	    public UserEntity getUtilizador() {
	        return utilizador;
	    }

	    public void setUtilizador(UserEntity utilizador) {
	        this.utilizador = utilizador;
	    }

	    @Override
	    public String toString() {
	        return "Lyric{" + "lyric_id=" + lyric_id + ", textLyric=" + textLyric + ", music=" + music + ", appuser=" + utilizador + '}';
	    }

	}
