package pt.dei.uc.converter;

import java.util.ArrayList;
import java.util.List;

import pt.dei.uc.RESTentities.*;
import dei.uc.pt.ar.paj.Entities.*;

public class ConverterEntityToWS {

	public ConverterEntityToWS() {
	}
	
	public static List<UserREST> convertUserEntityToUserWS(List<UserEntity> userlist){
		List<UserREST> userlistREST = new ArrayList<>();
		for(UserEntity user : userlist){
			UserREST userrest = new UserREST();
			userrest.setName(user.getName());
			userrest.setUserid(user.getUserId());
			userrest.setEmail(user.getEmail());
			userlistREST.add(userrest);			
		}
		
		return userlistREST;
	}
	
	public static UserREST convertUserEntityToUserWS(UserEntity user){
		UserREST userrest = new UserREST();
		userrest.setName(user.getName());
		userrest.setUserid(user.getUserId());
		userrest.setEmail(user.getEmail());
		userrest.setPassword(user.getPassword());
		userrest.setDatanascimento(user.getDatanascimento());		
		return userrest;
	}
}
