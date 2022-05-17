package com.song.blog.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User 클래스가 db에 테이블을 생성
//@DynamicInsert	// insert시에 null인 필드를 제외시켜줌
public class user {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;
	
	@Column(nullable=false, length =100, unique=true)
	private String username;
	
	@Column(nullable=false, length =100)
	private String password;
	
	@Column(nullable=false, length =50)
	private String email;
	
//	@ColumnDefault("'user'")	// ''를 붙여 문자라는 것을 알려주어야 함
	@Enumerated(EnumType.STRING)
	private RoleType role;

//	카카오, 구글, 네이버 등 로그인기능을 사용한 사용자인지 체크
	private String oauth;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(insertable=false, updatable=true)
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
