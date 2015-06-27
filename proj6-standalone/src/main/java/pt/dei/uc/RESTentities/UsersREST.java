package pt.dei.uc.RESTentities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
public class UsersREST {
	
	@XmlElement(name="user")
	private List<UserREST> users;

	public UsersREST() {
	}

	public List<UserREST> getUsers() {
		return users;
	}

	public void setUsers(List<UserREST> users) {
		this.users = users;
	}
	
	

}
