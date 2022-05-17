package com.song.blog.reposiotry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.blog.model.board;

@Repository
public interface boardRepository extends JpaRepository<board, Integer>{
	
	

}
