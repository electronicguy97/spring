package com.example.spring04.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring04.model.message.MessageDTO;
import com.example.spring04.service.message.MessageService;

@RestController // view와 데이터를 리턴할 수 있는 컨트롤러 
@RequestMapping("/messages/*")
public class MessageController {
	@Inject
	MessageService service;

	@RequestMapping("/")
	public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto) {
		ResponseEntity<String> entity = null; // 데이터와 상태코드 리턴
		try {
			service.insertMessage(dto); //트랜잭션 처리 
			entity = new ResponseEntity<>("success", HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
