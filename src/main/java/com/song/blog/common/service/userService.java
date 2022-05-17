package com.song.blog.common.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.song.blog.model.RoleType;
import com.song.blog.model.user;
import com.song.blog.reposiotry.userRepository;

@Service
public class userService {

	@Autowired
	private userRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void join(user user) {

		String rawPassword = user.getPassword(); // 원본 비번
		String encPassword = encoder.encode(rawPassword); // 해쉬 비번
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);

		userRepository.save(user);
	}

	@Transactional
	public void update(user user) {

		user persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("수정 실패 : 아이디를 찾을 수 없습니다. id : " + user.getId());
		});
		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
			persistance.setUpdateDate(LocalDateTime.now());
		}

	}

	@Transactional(readOnly = true)
	public user findUser(String username) {

		// TROWS는 못찾으면 에러이니 Get으로 빈객체 생성
		user user = userRepository.findByUsername(username).orElseGet(() -> {
			return new user();
		});
		return user;
	}

}
