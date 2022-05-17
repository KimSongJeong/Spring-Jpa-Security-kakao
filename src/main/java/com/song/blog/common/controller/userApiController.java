package com.song.blog.common.controller;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.common.config.auth.PrincipalDetail;
import com.song.blog.common.dto.ResponseDto;
import com.song.blog.common.service.userService;
import com.song.blog.model.user;

@RestController
public class userApiController {
	
	@Autowired
	private userService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> joinUser(@RequestBody user user) {
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> updateUser(@RequestBody user user) {
		
		userService.update(user);
		
		// 세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	

}
