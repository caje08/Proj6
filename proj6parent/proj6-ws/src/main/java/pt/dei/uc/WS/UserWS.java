package pt.dei.uc.WS;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pt.dei.uc.RESTentities.*;
import pt.dei.uc.converter.ConverterEntityToWS;
import dei.uc.pt.ar.paj.EJB.*;
import dei.uc.pt.ar.paj.Entities.UserEntity;


@Stateless
@Path("/user-mgmt")
public class UserWS {
	
	
	@EJB
	private UserEJBLocal userejb;
	
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
	
	

}
