package service;

import java.util.List;

import domain.User;

public interface UserService {
	void save(User user);
	User findById(Long id) throws Exception;
	List<User> findByUsername(String username);
	List<User> findByFirstname(String firstname);
}
