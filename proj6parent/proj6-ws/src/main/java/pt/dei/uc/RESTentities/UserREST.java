package pt.dei.uc.RESTentities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class UserREST {
	
	@XmlElement(name="userid")
	private long userid;
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="password")
	private String password;
	@XmlElement(name="email")
	private String email;
	@XmlElement(name="datanasc")
	private String datanascimento;
	
	private List<PlaylistREST> userplaylist;
	
	private List<MusicREST> usermusicas;

	public UserREST() {		
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public List<PlaylistREST> getUserplaylist() {
		return userplaylist;
	}

	public void setUserplaylist(List<PlaylistREST> userplaylist) {
		this.userplaylist = userplaylist;
	}

	public List<MusicREST> getUsermusicas() {
		return usermusicas;
	}

	public void setUsermusicas(List<MusicREST> usermusicas) {
		this.usermusicas = usermusicas;
	}

	
	

}
