package dei.uc.pt.ar.paj.web;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dei.uc.pt.ar.paj.EJB.PasswordEJB;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;

import java.io.Serializable;

@Named
@SessionScoped
public class EditUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1979160830875020805L;

	@EJB
	private PasswordEJB pw;
	@EJB
	private UserFacade userFacade;
	
	private String newUserName;
	private String oldPass;
	private String newPass;
	private String repeatPass;	
	private String newEmail;
	private String mensagem;

	private Date birth;

	private UserEntity activeUser;

	public void init(UserEntity activeUser){
		this.activeUser=activeUser;
		this.newUserName=this.activeUser.getName();
		this.newEmail=this.activeUser.getEmail();
		this.mensagem=this.oldPass=this.newPass=this.repeatPass="";
	}

	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public UserEntity getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(UserEntity activeUser) {
		this.activeUser = activeUser;
	}

	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public UserEntity saveUserChanges(){
		if(!this.newUserName.isEmpty())
			this.activeUser.setName(this.newUserName);

		if(!this.newEmail.isEmpty())
			this.activeUser.setEmail(this.newEmail);

		return this.activeUser;
	}

	public boolean saveUserPasswordChanges() {			
		return checkPw();
	}

	public boolean checkPw() {
		String p1=pw.encrypt(oldPass);
		String p2=pw.encrypt(this.newPass);
		UserEntity usertmp=userFacade.findByEmailPass(this.newEmail, p1);
		boolean sai=false;
		
		if(usertmp!= null){
			if(p1.equals(p2)){
				this.mensagem="New and old password must be different";
			}else{
				p1=pw.encrypt(this.repeatPass);
				if(!p2.equals(p1)){
					this.mensagem="New passwords don't match.";
				}else{
					sai=true;
					this.activeUser.setPassword(pw.encrypt(this.newPass));
					this.mensagem="Password has been update with success!!.";
				}
			}
		}else 
		 this.mensagem = "UserName "+this.newUserName+" was not found within BD with defined old password!";
	
	return sai;
	}
	
}
