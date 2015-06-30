package pt.dei.uc.wscomsumers;

import pt.dei.uc.RESTentities.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class UserWSConsumer {
	

	public UserWSConsumer() {
		
	}

	public static void getNumberOfUsers() {
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/user-mgmt/usernumber");
			Response response = target.request().get();
			System.out.println("The App has " + response.readEntity(String.class)
					+ " users.");
		} catch (NullPointerException e) {
			System.out.println("Could not connect to client!");
			e.printStackTrace();
		}

		
		
		
	}

	public static void getAllUsers() {		
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/all");

		Response response = target.request().get();

		UsersREST usersrest = response.readEntity(UsersREST.class);

		for (UserREST user : usersrest.getUsers()) {
			user.getInfo();
		}
		
		

	}

	public static void getUserInfo(int id) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/");
		
		target = target.path(""+id);
		
		Response response = target.request().get();
		
		UserREST user = response.readEntity(UserREST.class);
		
		user.getInfo();	
		
		
		
	}
	
	public static void getLoggedUserNumber(){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/loggedusersnumber");

		Response response = target.request().get();
		
		System.out.println("The App has " + response.readEntity(String.class)
				+ " logged users.");
		
		
	}
	
	public static void getLoggedUsers(){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/loggedusers");

		Response response = target.request().get();

		UsersREST usersrest = response.readEntity(UsersREST.class);

		for (UserREST user : usersrest.getUsers()) {
			user.getInfo();
		}
		
		
		
	}
	
	public static void removeUser(int id){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/remove/");
		
		target = target.path(""+id);

		Response response = target.request().get();
		
		UserREST user = response.readEntity(UserREST.class);
		
		System.out.println("The user with the following info was removed");
		
		user.getInfo();
		
		
	}
	
	public static void addUser(UserREST user){
		ResteasyClient client = new ResteasyClientBuilder().build();		
			
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/adduser");
		
		Response response = target.request(MediaType.APPLICATION_XML).post(
				Entity.entity(user, "application/xml"));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		System.out.println("User added successfully!");
		
		
	}
	
	public static void editUserPassword(int id, String password){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		UserREST user = new UserREST();		
		
		user.setUserid(id);
		user.setPassword(password);
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/setpassword");
		
		Response response = target.request(MediaType.APPLICATION_XML).post(
				Entity.entity(user, "application/xml"));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		System.out.println("The password was edited successfully!");
		
		
	}
	
	
	
	

}
