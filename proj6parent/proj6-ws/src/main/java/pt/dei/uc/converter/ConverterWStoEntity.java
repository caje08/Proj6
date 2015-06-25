package pt.dei.uc.converter;

import java.util.ArrayList;
import java.util.List;

import pt.dei.uc.RESTentities.*;
import dei.uc.pt.ar.paj.Entities.*;

public class ConverterWStoEntity {

	public ConverterWStoEntity() {
	}
	
	public static UserEntity convertUserRESTtoEntity(UserREST userrest){
		
		UserEntity userent = new UserEntity();
		userent.setName(userrest.getName());		
		userent.setEmail(userrest.getEmail());
		userent.setPassword(userrest.getPassword());
		userent.setDatanascimento(userrest.getDatanascimento());
		return userent;
	}

}
