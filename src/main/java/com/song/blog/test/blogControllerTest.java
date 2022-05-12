package com.song.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class blogControllerTest {
	
	@GetMapping("/")
	public String openCon() {
		return "<h1>hello</h1>"; 
	}
}
