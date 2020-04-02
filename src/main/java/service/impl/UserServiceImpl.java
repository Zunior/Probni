package service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.User;
import repository.UserRepository;
import service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findById(Long id) throws Exception {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new Exception("User does not exist");
	}

	@Override
	public List<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findByFirstname(String firstname) {
		return userRepository.findByFirstname(firstname);
	}

}
