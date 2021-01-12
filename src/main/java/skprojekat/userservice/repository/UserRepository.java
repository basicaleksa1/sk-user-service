package skprojekat.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skprojekat.userservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	void deleteById(int id);
	
    Optional<User> findUserByEmailAndPassword(String email, String password);

    Optional<User> findById(Integer id);

}
