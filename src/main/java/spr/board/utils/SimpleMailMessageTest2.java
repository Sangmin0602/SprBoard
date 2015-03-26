package spr.board.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import spr.board.model.Member;


public class SimpleMailMessageTest2 implements SendMailService {
	
	private MailSender mailSender;
    private SimpleMailMessage templateMailMessage;
     
 
    public MailSender getMailSender() {
		return mailSender;
	}


	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}


	public SimpleMailMessage getTemplateMailMessage() {
		return templateMailMessage;
	}


	public void setTemplateMailMessage(SimpleMailMessage templateMailMessage) {
		this.templateMailMessage = templateMailMessage;
	}


	@Override
    public void sendMail(Member member) {
        SimpleMailMessage message = new SimpleMailMessage(templateMailMessage);
        message.setText("회원 가입을 축하합니다.");
        message.setTo(member.getEmail());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    } // sendEmail
}
