package com.song.blog.common.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.song.blog.model.user;
import com.song.blog.reposiotry.userRepository;

@Service	// Bean 등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private userRepository userRepository;
	
	
	// 스프링이 로그인요청을 가로챌 때, username, password 변수 2개를 가로채는데,
	// password 부분 처리는 알아서 함
	// username이 DB에 있는지만 확인해주면 됨
	// 그 확인을 이 override된 함수에서 함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		user principal = userRepository.findByUsername(username)
				.orElseThrow(()-> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		
//		로그인 될 때, loadUserByUsername라는 함수가 자동적으로 실행
		return new PrincipalDetail(principal);	// 시큐리티의 세션에 유저 정보가 저장이 됨
	}
	
}
