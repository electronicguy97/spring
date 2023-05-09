package com.example.spring04.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring04.model.message.MessageDAO;
import com.example.spring04.model.message.MessageDTO;
import com.example.spring04.model.message.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	MessageDAO messageDao;
	
	@Inject
	PointDAO pointDao;

	@Transactional //트랜잭션 처리 함수 
	@Override
	public void insertMessage(MessageDTO dto) {
		messageDao.create(dto);
		pointDao.updatePoint(dto.getSender(), 10);
	}

}

