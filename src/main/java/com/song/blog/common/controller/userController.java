package com.song.blog.common.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.song.blog.common.config.auth.PrincipalDetail;
import com.song.blog.common.service.userService;
import com.song.blog.model.KakaoProfile;
import com.song.blog.model.OAuthToken;
import com.song.blog.model.user;

@Controller
public class userController {
	
	@Value("${cos.key")
	private String cosKey;
	
	@Autowired
	private userService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	인증이 되지 않은 사용자가 접속할 수 있는 경로 /auth
	// 인덱스인 / 도 허용, static 이하에 있는 /js , /css, /image 도 ... 허용
	// 나머지는 인증절차 필수
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "common/index/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "common/index/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		
		return "common/index/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {	// ResponseBody : Data를 리턴해주는 컨트롤러 함수
		
		
		// POST방식으로 key=value 데이터를 요청(카카오쪽으로)
		// RestTemplate 이외에도 Retrofit2, OKHttp 이 있음
		
		RestTemplate rt = new RestTemplate();
		
		// HttpHeaders : 오브젝터 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// HttpBodt 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "dcd90f43314bbee7e009c68decb994b9");
		params.add("redirect_uri", "http://localhost:8088/auth/kakao/callback");
		params.add("code", code);
		
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);
		
		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",	// 요청 주소
			HttpMethod.POST,	// 요청 방식
			kakaoTokenRequest,	// 전송데이터
			String.class	// 응답받을 타입
		);
		
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());
		
		
//		회원정보 요청
		RestTemplate rt2 = new RestTemplate();
		
		// HttpHeaders : 오브젝터 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		// Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",	// 요청 주소
			HttpMethod.POST,	// 요청 방식
			kakaoProfileRequest2,	// 전송데이터
			String.class	// 응답받을 타입
		);
		
				ObjectMapper objectMapper2 = new ObjectMapper();
				KakaoProfile kakaoProfile = null;
				try {
					kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
		
		// user 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
		System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("블로그 서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
		System.out.println("블로그 서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());
//		UUID garbagePassword = UUID.randomUUID();
		System.out.println("블로그 서버 패스워드 : " + cosKey);
		
		user kakaoUser = user.builder()
				.username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
//		가입자 혹은 비가입자 체크 해서 처리
		user originUser = userService.findUser(kakaoUser.getUsername());
		
		if (originUser.getUsername() == null) {
			// 가입되지 않았으면 회원가입 처리
			System.out.println("기존 회원이 아닙니다.................");
			userService.join(kakaoUser);
		}
		
		//로그인 처리
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
	
	
}
