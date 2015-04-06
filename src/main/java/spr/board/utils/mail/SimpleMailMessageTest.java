package spr.board.utils.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import spr.board.model.Member;

public class SimpleMailMessageTest implements SendMailService{
	
    private MailSender mailSender;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
	@Override
	public void sendMail(Member member) {
		SimpleMailMessage message = new SimpleMailMessage();
		
        message.setSubject("[공지] 회원 가입 안내");
        message.setText("회원 가입을 축하합니다.");
        message.setFrom("kyung_ho@naver.com");
        message.setTo(member.getEmail());
        
        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
		
	}

}
