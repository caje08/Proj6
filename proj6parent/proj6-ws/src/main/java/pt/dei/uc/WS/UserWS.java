package pt.dei.uc.WS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;





import pt.dei.uc.RESTentities.*;
import pt.dei.uc.converter.*;
import dei.uc.pt.ar.paj.ejb.*;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Stateless
@Path("/user-mgmt")
public class UserWS {
	
	
	@EJB
	private UserEJBLocal userejb;
	
	@EJB
	private PasswordEJB pw;
	
	@Inject 
	private UserFacade userfacade;
	
	static Logger logger = LoggerFactory.getLogger(UserWS.class);
	
	public UserWS() {
		
	}
	
	@GET
    @Path("/all")
    @Produces("application/xml")
	public Response getAllUsers()	
    {
		
		
			try {
				List<UserEntity> userlist = userejb.getUsers();		
				List<UserREST> userlistREST = ConverterEntityToWS.convertUserEntityToUserWS(userlist);
				UsersREST users = new UsersREST();
				users.setUsers(userlistREST);
				return Response.status(200).entity(users).build();
			} catch (NullPointerException e) {
				logger.info("No users!");
				return Response.status(200).build();
			}
		
        
    }
	
	@GET
    @Path("/usernumber")
    @Produces("application/xml")
	public Response getUsersSize(){
		List<UserEntity> userlist = userejb.getUsers();		
        List<UserREST> userlistREST = ConverterEntityToWS.convertUserEntityToUserWS(userlist);
        UsersREST users = new UsersREST();
        users.setUsers(userlistREST);
        NumberREST number = new NumberREST();
        number.setUsernumber(userlistREST.size());
        return Response.status(200).entity(number).build();
		
	}
	
	@GET
    @Path("{id: \\d+}")
    @Produces("application/xml")
	public Response getUserInfo(@PathParam("id") int id){
		UserREST userrest;
		try {
			UserEntity user;
			try {
				user = userejb.findById(id);
			} catch (Exception e) {
				logger.info("No user found!");
				return Response.status(200).build();
			}
			
			userrest = ConverterEntityToWS.convertUserEntityToUserWS(user);
			
			return Response.status(200).entity(userrest).build();
		} catch (NoResultException | NullPointerException e) {
			return Response.status(500).build();
		}
		
		
	}
	
	@GET
	@Path("/loggedusersnumber")
	@Produces("application/xml")
	public Response getLoggedUserNumber(){
		NumberREST number = new NumberREST();
		number.setUsernumber(UserEJB.getUserCount());
		return Response.status(200).entity(number).build();
	}
	
	@GET
	@Path("/loggedusers")
	@Produces("application/xml")
	public Response getLoggedUsers(){
		try {
			List<UserEntity> loggedusers = new ArrayList<UserEntity>();
			loggedusers.addAll(UserEJB.getLoggedUsers().keySet());
			List<UserREST> loggedusersREST = ConverterEntityToWS.convertUserEntityToUserWS(loggedusers);
			UsersREST users = new UsersREST();
			users.setUsers(loggedusersREST);
			return Response.status(200).entity(users).build();
		} catch (NullPointerException e) {
			return Response.status(304).build();
		}
	}
	
	@Path("/remove/{id: \\d+}")
	@GET
	@Produces("application/xml")	
	public Response removeUser(@PathParam("id") int id ){
		List<UserEntity> userlist = userejb.getUsers();		
        UserREST userrest = new UserREST();
        
        for(UserEntity user : userlist){
        	if(id == user.getUserId()){
        		userfacade.remove(user);
        		userrest = ConverterEntityToWS.convertUserEntityToUserWS(user);
        	}
        }      
        
        return Response.status(200).entity(userrest).build();
	}
	
	@Path("/adduser")
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response addUser(UserREST user){
		user.setPassword(pw.encrypt(user.getPassword()));
		UserEntity userent = ConverterWStoEntity.convertUserRESTtoEntity(user);
		
		try {
			userfacade.create(userent);
		} catch (Exception e) {			
			return Response.notModified().build();
		}
		
		return Response.status(200).entity(user).build();		
	}
	
	@Path("/setpassword")
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response editPassword(UserREST user){
		
		UserEntity userent;
		try {
			userent = userejb.findById(user.getUserid());
			userent.setPassword(pw.encrypt(user.getPassword()));
		} catch (Exception e1) {
			return Response.notModified().build();
		}		
		
		
		
		try {
			userfacade.merge(userent);
		} catch (Exception e) {			
			return Response.notModified().build();
		}
		
		return Response.status(200).build();		
	}
	
	
	
	
	
	
	

}
