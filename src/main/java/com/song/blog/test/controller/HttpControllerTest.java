package com.song.blog.test.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.test.vo.Member;

// 사용자가 요청  -> 응답(Html) = Controller
// 사용자가 요청 -> 응답(Data) = RestController



@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("/api/lombok")
	public String lombokTest() {
		Member m = new Member(1, "song", "1234", "email");

		System.out.println(TAG + "getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "getter : " + m.getId());
		return "lombok test 완료";
	}

	
	@GetMapping("/api/get")
	public String getTest(@RequestParam Map<String, Object> map) {
		return "get 요청" + map.get("id") + "," + map.get("username");
	}
	
	@PostMapping("/api/post")
	public String postTest() {
		return "post 요청";
	}
	
	@PutMapping("/api/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/api/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
