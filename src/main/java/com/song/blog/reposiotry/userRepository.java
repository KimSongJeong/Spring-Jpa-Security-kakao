package com.song.blog.reposiotry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.blog.model.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer>{
	
	

}
