package com.reis.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reis.workshopmongo.domain.User;
import com.reis.workshopmongo.dto.UserDTO;
import com.reis.workshopmongo.repository.UserRepository;
import com.reis.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll (){
		return repo.findAll();
				
	}
	
	public User findById (String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
	  return repo.insert(obj);
	}
	
	public User update (User obj) {
		User newOBJ = findById(obj.getId());
		updateData(newOBJ, obj);
		return repo.save(newOBJ);
	}
	
	private void updateData(User newOBJ, User obj) {
		newOBJ.setEmail(obj.getEmail());
		newOBJ.setName(obj.getName());
	}

	public void delete (String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO (UserDTO objDTO) {
		return new User (objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
