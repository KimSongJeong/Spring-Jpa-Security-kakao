package com.song.blog.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.song.blog.common.dto.ReplySaveRequestDto;
import com.song.blog.model.board;
import com.song.blog.model.reply;
import com.song.blog.model.user;
import com.song.blog.reposiotry.boardRepository;
import com.song.blog.reposiotry.replyRespository;
import com.song.blog.reposiotry.userRepository;

@Service
public class boardService {
	
	@Autowired
	private boardRepository boardRepository;
	
	@Autowired
	private replyRespository replyRespository;
	
	@Autowired
	private userRepository userRepository;

	public void save(board board, user user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly=true)
	public Page<board> Boardlist(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	
	@Transactional(readOnly=true)
	public board findById(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()-> {
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다. id : " + id);
				});
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void update(int id, board requestboard) {
		board board = boardRepository.findById(id)
				.orElseThrow(()-> {
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다. id : " + id);
				});
		
		board.setTitle(requestboard.getTitle());
		board.setContent(requestboard.getContent());
	}

	
//	댓글 생성
	@Transactional
	public void replySave(ReplySaveRequestDto replySaveRequestDto) {
		
//		방법 1
		int result = replyRespository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
		System.out.println(result);
//		방법 2
//		board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()-> {
//			return new IllegalArgumentException("댓글 작성 실패, 게시글 아이디를 찾을 수 없습니다. id : " + replySaveRequestDto.getBoardId());
//		});
//		
//		user user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()-> {
//			return new IllegalArgumentException("댓글 작성 실패, 사용자 아이디를 찾을 수 없습니다. id : " + replySaveRequestDto.getUserId());
//		});
//		
//		
//		reply replyDate = reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
		
//		방법 3
//		reply model에 아래를 작성하고, 아래처럼 해도됨 	
// 		public void update(user user, board board, String content) {
//		setUser(user);
//		setBoard(board);
//		setContent(content);
//	}
//		replyDate.update(user, board, replySaveRequestDto.getContent());
		
//		replyRespository.save(replyDate);
	}

//	댓글 삭제
	@Transactional
	public void replyDelete(int replyId) {
		replyRespository.deleteById(replyId);
	}

}
