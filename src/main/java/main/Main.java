package main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import config.MyAppConfig;
import domain.User;
import service.UserService;

@Component
public class Main {
	private final UserService userService;
	
	@Autowired
	Main(UserService userService){
		this.userService = userService;
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);
		Main main = applicationContext.getBean(Main.class);
//		main.saveUser("ana", "anic", "ana", "ana");
//		main.findUserById(3L);
		
//		main.findByUsername("pera");
		main.findByFirstName("pera");
		
	}
	
	private void findByFirstName(String firstname) {
		List<User> users = userService.findByFirstname(firstname);
		for (User user : users) {
			System.out.println(user);
		}
		
	}

	private void findByUsername(String username) {
		List<User> users = userService.findByUsername(username);
		for (User user : users) {
			System.out.println(user);
		}
		
	}

	private void saveUser(String firstname, String lastname, String username, String password) {
		User user = new User(null, firstname, lastname, username, password);
		userService.save(user);
	}
	
	private void findUserById(Long id) {
		try {
			User user = userService.findById(id);
			System.out.println("Find: " + user);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
