package dei.uc.pt.ar.paj.login;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import dei.uc.pt.ar.paj.ejb.PasswordEJB;
import dei.uc.pt.ar.paj.ejb.UserEJB;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;
import dei.uc.pt.ar.paj.web.ActiveSession;


/**
 *
 * @author 
 */
@Named("loginMB")
@SessionScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = -6202006843421064331L;
	static Logger logger = LoggerFactory.getLogger(LoginMB.class);
	//The credentials to search for
	private String email;
	private String password;
	private String errorMessage;
	@EJB
	private UserFacade userFacade;

	@EJB
	private PasswordEJB pw;
	@Inject
	private UserSession userSession;

	@Inject
	ActiveSession session;

	@Inject
	VirtualEJB ejb;

	/**
	 * Creates a new instance of LoginMB
	 */
	public LoginMB() {
	}

	public PasswordEJB getPw() {
		return pw;
	}

	public void setPw(PasswordEJB pw) {
		this.pw = pw;
	}

	public UserSession getuserSession() {
		return userSession;
	}

	public void setuserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserFacade getuserFacade() {
		return userFacade;
	}

	public void setuserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public String searchUser() {

		logger.info("Entrou no LoginMB.searchUser()");
		String passw=pw.encrypt(password);
		UserEntity tmp =null;
		try {
			tmp = userFacade.findByEmailPass(email, passw);
			if(tmp==null){
				this.errorMessage = "Email/Password combination not found! Please try again";
				this.password="";
				this.email="";
				logger.error(this.errorMessage);
				return "login";
			}
		} catch (Exception pe) {
		//	System.out.println(pe.getMessage());
			logger.error(pe.getMessage());
			this.errorMessage = "Email/Password combination not found! Please try again";
			this.password="";
			this.email="";
			return "login";
		}    	

		if(!(tmp.equals(null))){
			userSession.setLoggedUser((UserEntity)tmp);
			if (userSession.getLoggedUser() != null) {
				logger.info("Logged_user= "+this.email);
				session.init(tmp);
				redirect();        	
				//        	doLogin(0);  
				return "index";
			} else {
				this.errorMessage = "Email/Password combination not found! Please try again";
				logger.error(this.errorMessage);
				return "login";
			}
		}else {
			this.errorMessage = "Email/Password combination not found! Please try again";
			logger.error(this.errorMessage);
			return "login";
		}
		//    	String passw=pw.encrypt(password);
		//    	UserEntity usertmp1=null;
		//        userSession.setLoggedUser(userFacade.findByEmailPass(email, passw));
		//        if (userSession.getLoggedUser() != null) {
		//        	logger.info("Logged_user= "+this.email);
		////        	doLogin(0);  
		//        	usertmp1=getLoggedUser();
		//        	session.init(usertmp1);
		//    		redirect();
		//            return "index";
		//        } else {
		//            this.errorMessage = "Email/Password combination not found! Please try again";
		//            return "login";
		//        }
	}

	public String refreshUser() {
		userSession.setLoggedUser(userFacade.findByEmailPass(userSession.getLoggedUser().getEmail(), userSession.getLoggedUser().getPassword()));
		if (userSession.getLoggedUser() != null) {

			return "index";
		} else {
			this.errorMessage = "Email/Password combination not found! Please try again";
			return "login";
		}
	}

	public void logged() throws IOException{
		if(userSession.getLoggedUser() != null){
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "index.xhtml");
		}
	}

	public void notLogged() throws IOException {
		if (userSession.getLoggedUser() == null) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
		}            
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
				
		return "login.xhtml?faces-redirect=true";
	}

	public UserEntity getLoggedUser(){
		return userSession.getLoggedUser();
	}
	
	public void doLogin(){		
		searchUser();		
	}

	private void redirect(){

		String redirect="index.xhtml";
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
