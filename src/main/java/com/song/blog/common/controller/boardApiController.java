package com.song.blog.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.common.config.auth.PrincipalDetail;
import com.song.blog.common.dto.ReplySaveRequestDto;
import com.song.blog.common.dto.ResponseDto;
import com.song.blog.common.service.boardService;
import com.song.blog.model.board;
import com.song.blog.model.reply;


@RestController
public class boardApiController {

	
	@Autowired
	private boardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> boardSave(@RequestBody board board, @AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.save(board, principal.getUser());

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> boardDelete(@PathVariable int id) {
		boardService.delete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> boardUpdate(@PathVariable int id, @RequestBody board board) {
		
		boardService.update(id, board);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	
	// 데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	// dto 사용하지 않는 이유 : 작은 프로젝트의 경우 필요 X
	// 프로젝트가 커지면 커질수록 수많은 데이터들이 model의 object로만 받는것은 효율적이지 못함
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		// @PathVariable int boardId, @RequestBody reply reply,  @AuthenticationPrincipal PrincipalDetail principal라고 매게변수 받는것들이 
//		ReplySaveRequestDto reply라는 짧은코드로 변화됨
		boardService.replySave(replySaveRequestDto);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
		boardService.replyDelete(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
