package com.song.blog.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.song.blog.common.config.auth.PrincipalDetailService;


// 아래의 세개는 세트
@Configuration 			// 빈등록(IOC 관리) : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity		// 시큐리티라는 필터 추가 - 컨트롤러로가서 request가 실행되기 전에 이것이 먼저 실행되게 하는 것 즉, 시큐리티 필터가 등록이 된다. 그 설정을 SecurityConfig라는 클래스에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Bean	// IOC화
	public BCryptPasswordEncoder encodePWD() {
		// BCryptPasswordEncdoe : 어떤 값을 해쉬화해줌, 암호화
		return new BCryptPasswordEncoder();
	}
	
	
	// 시큐리티가 대신 로그인해주는데 그때, password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()			// csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeHttpRequests()	// 요청이 들어오면 아래를
			.antMatchers("/", "/auth/**", "/resources/**")	// /auth가 붙으면
			.permitAll()				// 인증되지 않아도 허용
			.anyRequest()				// 위의 것이 아닌 모든 요청은
			.authenticated()			// 인증이 되어야 한다
		.and()
			.formLogin()					// 인증되지 않은 모든 요청은 loginform으로 오도록 설정
			.loginPage("/auth/loginForm")	// 로그인 페이지 설정 (도메인값)
			.loginProcessingUrl("/auth/loginProc")		// 스프링 시큐리티가 해당 주소로 요청되는 로그인을 가로채서 대신 로그인 해준다. (컨트롤러 따로 만들필요없음)
			.defaultSuccessUrl("/");	// 정상적으로 로그인 될 경우 이동 url, 실패시에는 fail___ 머시기
	}
	
}
