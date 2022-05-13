package com.song.blog.test.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.model.RoleType;
import com.song.blog.model.user;
import com.song.blog.reposiotry.userRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private userRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(user user) {

		System.out.println("데이터 확인 : " + user);

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다.";
	}

	@GetMapping("/dummy/user/{id}")
	public user detail(@PathVariable int id) {

		user user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 사용자는 없습니다.");
		});

		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<user> list() {
		
		return userRepository.findAll();
		
	}
	
	
	@GetMapping("/dummy/user")
	public Page<user> pageList(@PageableDefault(size=2, sort="id",direction=Sort.Direction.DESC) Pageable pageable) {
		
		Page<user> users = userRepository.findAll(pageable);
		
		return users;
	}
	
	@Transactional
//	get요청과 요청url이 같지만, 알아서 구분해서 사용됨 put/get
	@PutMapping("/dummy/user/{id}")
	public user updateUser(@PathVariable int id, @RequestBody user requestUser) {
		
		user user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUpdateDate(LocalDateTime.now());
		
//		방법 1
//		userRepository.save(user);
		
//		방법 2
//		단순하게 save안쓰고 @Transactional 쓰면 그냥반영되있음
		
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		}catch (Exception e) {
			return "없는 아이디, 삭제에 실패하였습니다. id : " + id;
		}
		
		return "삭제되었습니다. id : " + id;
	}
	
}
