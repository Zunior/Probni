package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
//	@Query("SELECT u FROM User u WHERE u.username=?1")
	List<User> findByUsername(String username);
	List<User> findByFirstname(String firstname);
}
