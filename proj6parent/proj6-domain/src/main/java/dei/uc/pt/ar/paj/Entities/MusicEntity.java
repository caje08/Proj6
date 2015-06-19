package dei.uc.pt.ar.paj.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity implementation class for Entity: Utilizador
 *
 */
@Entity
@Table(name="musica")
@NamedQueries({
	@NamedQuery(name = "Music.findByOwner", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId"),
	@NamedQuery(name = "Music.findAll", query="SELECT m FROM MusicEntity m"),
	@NamedQuery(name = "Music.findByAlbum", query="SELECT m FROM MusicEntity m WHERE m.album like :album"),
	@NamedQuery(name = "Music.findByAno", query="SELECT m FROM MusicEntity m WHERE m.anolancamento like :year"),

	
	//Search
	@NamedQuery(name = "Music.findBySearch", query = "SELECT m FROM MusicEntity m WHERE LOWER(m.nomemusica) LIKE :searchTerm OR LOWER(m.interprete) LIKE :searchTerm"),
	@NamedQuery(name = "Music.findByNomeMusica", query="SELECT m FROM MusicEntity m WHERE LOWER(m.nomemusica) LIKE :searchTerm"),
	@NamedQuery(name = "Music.findByInterprete", query="SELECT m FROM MusicEntity m WHERE LOWER(m.interprete) LIKE :searchTerm"),
	

	//Pesquisas de ordenação
	@NamedQuery(name = "Music.findAllOrderByNomeMusicaAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.nomemusica ASC"),
	@NamedQuery(name = "Music.findAllOrderByNomeMusicaDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.nomemusica DESC"),

	@NamedQuery(name = "Music.findAllOrderByInterpreteAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.interprete ASC"),
	@NamedQuery(name = "Music.findAllOrderByInterpreteDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.interprete DESC"),

	@NamedQuery(name = "Music.findAllOrderByTimeAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.length ASC"),
	@NamedQuery(name = "Music.findAllOrderByTimeDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.length DESC"),

	@NamedQuery(name = "Music.findAllOrderByAlbumAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.album ASC"),
	@NamedQuery(name = "Music.findAllOrderByAlbumDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.album DESC"),

	@NamedQuery(name = "Music.findAllOrderByAnolancamentoAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.anolancamento ASC"),
	@NamedQuery(name = "Music.findAllOrderByAnolancamentoDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.anolancamento DESC"),

	@NamedQuery(name = "Music.findAllOrderByOwnerAscending", query = "SELECT m FROM MusicEntity m ORDER BY m.utilizador ASC"),
	@NamedQuery(name = "Music.findAllOrderByOwnerDescending", query = "SELECT m FROM MusicEntity m ORDER BY m.utilizador DESC"),


	//Pesquisas de ordenação por OWNER
	@NamedQuery(name = "Music.findByOwnerOrderByNomeMusicaAscending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.nomemusica ASC"),
	@NamedQuery(name = "Music.findByOwnerOrderByNomeMusicaDescending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.nomemusica DESC"),

	@NamedQuery(name = "Music.findByOwnerOrderByInterpreteAscending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.interprete ASC"),
	@NamedQuery(name = "Music.findByOwnerOrderByInterpreteDescending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.interprete DESC"),

	@NamedQuery(name = "Music.findByOwnerOrderByTimeAscending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.length ASC"),
	@NamedQuery(name = "Music.findByOwnerOrderByTimeDescending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.length DESC"),

	@NamedQuery(name = "Music.findByOwnerOrderByAlbumAscending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.album ASC"),
	@NamedQuery(name = "Music.findByOwnerOrderByAlbumDescending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.album DESC"),

	@NamedQuery(name = "Music.findByOwnerOrderByAnolancamentoAscending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.anolancamento ASC"),
	@NamedQuery(name = "Music.findByOwnerOrderByAnolancamentoDescending", query = "SELECT m FROM MusicEntity m WHERE m.utilizador = :ownerId ORDER BY m.anolancamento DESC"),

	//	@NamedQuery(name = "Music.findByUser", query="SELECT m FROM MusicEntity m WHERE m.utilizador like :user"),
})
public class MusicEntity implements Serializable {

	public static final String FIND_ALL = "Music.findAll";
	public static final String FIND_BY_ALBUM = "Music.findByAlbum";
	public static final String FIND_BY_ANO = "Music.findByAno";
	public static final String FIND_BY_OWNER = "Music.findByOwner";
	
	// Search
	public static final String FIND_BY_SEARCH = "Music.findBySearch";
	public static final String FIND_BY_NOMEMUSICA = "Music.findByNomeMusica";
	public static final String FIND_BY_INTERPRETE = "Music.findByInterprete";


	//Pesquisas de ordenação
	public static final String FIND_ALL_ORDER_BY_NOME_ASC = "Music.findAllOrderByNomeMusicaAscending"; 
	public static final String FIND_ALL_ORDER_BY_NOME_DESC = "Music.findAllOrderByNomeMusicaDescending"; 

	public static final String FIND_ALL_ORDER_BY_INTERPRETE_ASC = "Music.findAllOrderByInterpreteAscending"; 
	public static final String FIND_ALL_ORDER_BY_INTERPRETE_DESC = "Music.findAllOrderByInterpreteDescending"; 

	public static final String FIND_ALL_ORDER_BY_TIME_ASC = "Music.findAllOrderByTimeAscending"; 
	public static final String FIND_ALL_ORDER_BY_TIME_DESC = "Music.findAllOrderByTimeDescending"; 

	public static final String FIND_ALL_ORDER_BY_ALBUM_ASC = "Music.findAllOrderByAlbumAscending"; 
	public static final String FIND_ALL_ORDER_BY_ALBUM_DESC = "Music.findAllOrderByAlbumDescending";

	public static final String FIND_ALL_ORDER_BY_YEAR_ASC = "Music.findAllOrderByAnolancamentoAscending"; 
	public static final String FIND_ALL_ORDER_BY_YEAR_DESC = "Music.findAllOrderByAnolancamentoDescending";

	public static final String FIND_ALL_ORDER_BY_OWNER_ASC = "Music.findAllOrderByOwnerAscending"; 
	public static final String FIND_ALL_ORDER_BY_OWNER_DESC = "Music.findAllOrderByOwnerDescending";



	//Pesquisas de ordenação por OWNER
	public static final String FIND_BY_OWNER_ORDER_BY_NOME_ASC = "Music.findByOwnerOrderByNomeMusicaAscending"; 
	public static final String FIND_BY_OWNER_ORDER_BY_NOME_DESC = "Music.findByOwnerOrderByNomeMusicaDescending"; 

	public static final String FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC = "Music.findByOwnerOrderByInterpreteAscending"; 
	public static final String FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC = "Music.findByOwnerOrderByInterpreteDescending"; 

	public static final String FIND_BY_OWNER_ORDER_BY_TIME_ASC = "Music.findByOwnerOrderByTimeAscending"; 
	public static final String FIND_BY_OWNER_ORDER_BY_TIME_DESC = "Music.findByOwnerOrderByTimeDescending"; 

	public static final String FIND_BY_OWNER_ORDER_BY_ALBUM_ASC = "Music.findByOwnerOrderByAlbumAscending"; 
	public static final String FIND_BY_OWNER_ORDER_BY_ALBUM_DESC = "Music.findByOwnerOrderByAlbumDescending";

	public static final String FIND_BY_OWNER_ORDER_BY_YEAR_ASC = "Music.findByOwnerOrderByAnolancamentoAscending"; 
	public static final String FIND_BY_OWNER_ORDER_BY_YEAR_DESC = "Music.findByOwnerOrderByAnolancamentoDescending";

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "musicid", nullable = false, unique = true)
	private Long musicid;
	//	private int musicid;
	@NotNull
	@Column(name = "nomemusica", nullable = false)
	private String nomemusica;
	@NotNull
	@Column(name = "interprete", nullable = false)
	private String interprete;
	@NotNull
	@Column(name = "album", nullable = false)
	private String album;
	@NotNull
	@Column(name = "anolancamento", nullable = false)
	private String anolancamento;

	@NotNull
	@Column(name = "time")
	private double length;
	//
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	private UserEntity utilizador;

	@NotNull
	@Column(name = "path", nullable = false)
	private String path;

	@ManyToMany(mappedBy = "songs")
	private List<PlaylistEntity> playlists;

	@Column(name = "datamusica", nullable = false)
	private String datamusica;

	@Column(name = "tipomusica", nullable = false)
	private String tipomusica;  

	public static enum Ordering {
		FIND_ALL, FIND_BY_INTERPRETE, FIND_BY_NOMEMUSICA, FIND_BY_ALBUM, FIND_BY_ANO, FIND_BY_OWNER,

		//Pesquisas de ordenação
		FIND_ALL_ORDER_BY_NOME_ASC, FIND_ALL_ORDER_BY_NOME_DESC, FIND_ALL_ORDER_BY_INTERPRETE_ASC, FIND_ALL_ORDER_BY_INTERPRETE_DESC, FIND_ALL_ORDER_BY_TIME_ASC, FIND_ALL_ORDER_BY_TIME_DESC,
		FIND_ALL_ORDER_BY_ALBUM_ASC, FIND_ALL_ORDER_BY_ALBUM_DESC, FIND_ALL_ORDER_BY_YEAR_ASC, FIND_ALL_ORDER_BY_YEAR_DESC, FIND_ALL_ORDER_BY_OWNER_ASC, FIND_ALL_ORDER_BY_OWNER_DESC,
		
		//Pesquisas de ordenação por OWNER
		FIND_BY_OWNER_ORDER_BY_NOME_ASC, FIND_BY_OWNER_ORDER_BY_NOME_DESC, FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC, FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC, FIND_BY_OWNER_ORDER_BY_TIME_ASC, FIND_BY_OWNER_ORDER_BY_TIME_DESC,
		FIND_BY_OWNER_ORDER_BY_ALBUM_ASC, FIND_BY_OWNER_ORDER_BY_ALBUM_DESC, FIND_BY_OWNER_ORDER_BY_YEAR_ASC, FIND_BY_OWNER_ORDER_BY_YEAR_DESC
	};
	static Logger logger = LoggerFactory.getLogger(MusicEntity.class); 

	public MusicEntity() {
		super();
		this.playlists=new ArrayList<PlaylistEntity>();
	}

	public MusicEntity(String nomemusica, String interprete, String album,
			String anolancamento, UserEntity owner, String path,
			String datamusica, String tipomusica) {
		super();
		this.nomemusica = nomemusica;
		this.interprete = interprete;
		this.album = album;
		this.anolancamento = anolancamento;
		this.utilizador = owner;
		this.path = path;
		this.datamusica = datamusica;
		this.tipomusica = tipomusica;
		this.length=0;

		this.playlists=new ArrayList<PlaylistEntity>();
	}

	public Long getMusicid(){
		return this.musicid;
	}

	public UserEntity getOwner() {
		return utilizador;
	}

	public void setOwner(UserEntity owner) {
		this.utilizador = owner;
	}

	public void setMusicid(Long musicid) {
		this.musicid = musicid;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public List<PlaylistEntity> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistEntity> playlists) {
		this.playlists = playlists;
	}

	public String getNomemusica() {
		return nomemusica;
	}

	public void setNomemusica(String nomemusica) {
		this.nomemusica = nomemusica;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAnolancamento() {
		return anolancamento;
	}

	public void setAnolancamento(String anolancamento) {
		this.anolancamento = anolancamento;
	}

	public UserEntity getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(UserEntity utilizador) {
		this.utilizador = utilizador;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDatamusica() {
		return datamusica;
	}

	public void setDatamusica(String datamusica) {
		this.datamusica = datamusica;
	}

	public String getTipomusica() {
		return tipomusica;
	}

	public void setTipomusica(String tipomusica) {
		this.tipomusica = tipomusica;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (musicid != null ? musicid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MusicEntity)) {
			return false;
		}
		MusicEntity other = (MusicEntity) object;
		return (this.musicid != null || other.musicid == null) && (this.musicid == null || this.musicid.equals(other.musicid));
	}

	@Override
	public String toString() {
		return "musicEntity[ id=" + musicid + " ]";
	}
}
