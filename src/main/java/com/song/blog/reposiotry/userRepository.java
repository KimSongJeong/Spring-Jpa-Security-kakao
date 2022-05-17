package com.song.blog.reposiotry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.blog.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer>{
	
	Optional<user> findByUsername(String username);
	
	// user findByEmailAndPassword(String email, String password);
	

}
