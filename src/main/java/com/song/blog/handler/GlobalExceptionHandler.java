package com.song.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.song.blog.common.dto.ResponseDto;


@ControllerAdvice	// 어느곳에서든 exception이 발생하더라도 이곳으로 올 수 있도록 달아주는 것
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)	// Exception이 발생하면 얘가 실행
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		// HttpStatus.INTERNAL_SERVER_ERROR.value() = 500
	}
	
}
