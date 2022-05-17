package com.song.blog.common.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.song.blog.model.user;

import lombok.Data;


// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Data
public class PrincipalDetail implements UserDetails{

	private user user;	// PrincipalDetail는 user를 가지고있다. 콤포지션
						// extends해서 들고오는 건 상속

//	생성자
	public PrincipalDetail(user user) {
		this.user = user;
	}
	
	
	// alrt + shift + s => Override/Implement Methods... 선택 후, 오버라이드
	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 안잠겨있는지 (true : 잠겨있지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴 (true : 만료되지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화(사용가능)가 되어있는지 리턴 (true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 어떤 권한을 가지고있는지 리턴해줌 (권한이 여러개 있을 수 있는데 이 예제는 한개만 존재)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//		리턴타입이 이상한거라 이렇게 해줘야 함
		Collection<GrantedAuthority> collectors = new ArrayList<>(); // ArrayList는 Collection 타입이기때문에 ArrayList
		collectors.add(() -> {return "ROLE_"+user.getRole();});
		
		return collectors;
	}
	
	
	
}
