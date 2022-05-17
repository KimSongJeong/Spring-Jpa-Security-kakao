package com.song.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터일때 Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne	// Many = Board, User = One
	@JoinColumn(name="userId")
	private user user;
	
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER, cascade=CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아님, FK가 아닌 것, DB에 컬럼을 만들지 않음, cascade=CascadeType.REMOVE : 보드게시글 지울때 한꺼번에 날리겠다 댓글까지
	@JsonIgnoreProperties({"board"})	// 무한 참조 방지, 무한참조되면 뻗어버림 ㅇㅇ
	@OrderBy("id desc")	// 내림차순으로 정렬
	private List<reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
