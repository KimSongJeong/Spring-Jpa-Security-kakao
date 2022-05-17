package com.song.blog.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.model.board;
import com.song.blog.reposiotry.boardRepository;

@RestController
public class replyControllerTest {
	
	@Autowired
	private boardRepository boardRepository;
	
	@GetMapping("/test/board/{id}")
	public board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
	}

}
