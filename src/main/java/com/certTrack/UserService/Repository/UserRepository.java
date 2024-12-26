package com.certTrack.UserService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.certTrack.UserService.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
