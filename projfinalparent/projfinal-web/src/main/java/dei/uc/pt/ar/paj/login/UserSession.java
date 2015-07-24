package dei.uc.pt.ar.paj.login;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import dei.uc.pt.ar.paj.Entities.UserEntity;

/**
 *
 * @author 
 */
@Stateful
@SessionScoped
public class UserSession {
    
    private UserEntity loggedUser;
//    private Long loggedId;
//    private String name;
//    private String email;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public UserSession() {
    }

    public UserEntity getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserEntity loggedUser) {
        this.loggedUser = loggedUser;
    }

    
}
