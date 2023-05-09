package com.example.spring04.service.email;

import com.example.spring04.model.email.EmailDTO;

public interface EmailService {
	void sendMail(EmailDTO dto);
}
