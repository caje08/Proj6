package dei.uc.pt.ar.paj.ejb;

import java.util.List;

import javax.ejb.Local;

import dei.uc.pt.ar.paj.Entities.UserEntity;

@Local
public interface UserEJBLocal {
	public void populate();

	public List<UserEntity> getUsers();

	public UserEntity findByEmail(String email);

	public UserEntity findById(long id);



}