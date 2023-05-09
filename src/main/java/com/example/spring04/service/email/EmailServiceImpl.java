package com.example.spring04.service.email;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring04.model.email.EmailDTO;

@Service // 서비스 bean
public class EmailServiceImpl implements EmailService {
	@Inject
	JavaMailSender mailSender; //root-context.xml에 정의한 이메일 bean
	@Override
	public void sendMail(EmailDTO dto) {
		try {
			MimeMessage msg=mailSender.createMimeMessage(); //이메일 객체
			//수신자 추가
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveMail()));
			//발신자 이메일과 이름
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(dto.getSenderMail(), dto.getSenderName())
			});
			msg.setSubject(dto.getSubject(),"utf-8"); //제목
			msg.setText(dto.getMessage(),"utf-8"); //본문
			mailSender.send(msg); //이메일 전송
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
