package com.song.blog.common.controller;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.song.blog.common.service.boardService;


@Controller
public class BoardController {

	@Autowired
	private boardService boardService;
	
	
//	메인페이지
	@GetMapping(value={"", "/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		
		model.addAttribute("boards", boardService.Boardlist(pageable));
		
		return "common/index/index";
	}
	
	
//	게시글 작성페이지
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "common/board/saveForm";
	}
	
//	게시글 상세페이지
	@GetMapping("/board/{id}")
	public String boardContent(Model model, @PathVariable int id) {
		
		model.addAttribute("board", boardService.findById(id));
		
		
		return "common/board/boardDetail";
	}
	
//	게시글 수정페이지
	@GetMapping("/board/{id}/updateForm")
	public String boardUpdate(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.findById(id));
		
		return "common/board/updateForm";
	}
	
}