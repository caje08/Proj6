package pt.dei.uc.WS;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pt.dei.uc.RESTentities.*;
import pt.dei.uc.converter.*;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.*;
import dei.uc.pt.ar.paj.ejb.*;


@Stateless
@Path("/user-mgmt")
public class UserWS {
	
	
	@EJB
	private UserEJBLocal userejb;
	
	@EJB
	private PasswordEJB pw;
	
	@Inject 
	private UserFacade userfacade;
	
	public UserWS() {
		
	}
	
	@GET
    @Path("/all")
    @Produces("application/xml")
	public Response getAllUsers()	
    {
		
		List<UserEntity> userlist = userejb.getUsers();		
        List<UserREST> userlistREST = ConverterEntityToWS.convertUserEntityToUserWS(userlist);
        UsersREST users = new UsersREST();
        users.setUsers(userlistREST);
        return Response.status(200).entity(users).build();
    }
	
	@GET
    @Path("/usernumber")
    @Produces("text/plain")
	public int getUsersSize(){
		List<UserEntity> userlist = userejb.getUsers();		
        List<UserREST> userlistREST = ConverterEntityToWS.convertUserEntityToUserWS(userlist);
        UsersREST users = new UsersREST();
        users.setUsers(userlistREST);
        return userlistREST.size();
		
	}
	
	@GET
    @Path("{id: \\d+}")
    @Produces("application/xml")
	public Response getUserInfo(@PathParam("id") int id){
		UserEntity user = userejb.findById(id);
		UserREST userrest = ConverterEntityToWS.convertUserEntityToUserWS(user);
		
		return Response.status(200).entity(userrest).build();
	}
	
	@GET
	@Path("/loggedusersnumber")
	@Produces("text/plain")
	public int getLoggedUserNumber(){
		return UserEJB.getUserCount();
	}
	
	@GET
	@Path("/loggedusers")
	@Produces("application/xml")
	public Response getLoggedUsers(){
		List<UserEntity> loggedusers = UserEJB.getLoggedUsers();
		List<UserREST> loggedusersREST = ConverterEntityToWS.convertUserEntityToUserWS(loggedusers);
		UsersREST users = new UsersREST();
        users.setUsers(loggedusersREST);
        return Response.status(200).entity(users).build();
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
		
		return Response.status(200).build();		
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
