package com.example.spring04.model.message;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void create(MessageDTO dto) {
		sqlSession.insert("message.create", dto);
	}

}
