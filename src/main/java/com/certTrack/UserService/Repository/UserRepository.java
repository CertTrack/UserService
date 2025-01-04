package com.certTrack.UserService.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.certTrack.UserService.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
