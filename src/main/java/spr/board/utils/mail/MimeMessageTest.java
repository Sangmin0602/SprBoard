package spr.board.utils.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import spr.board.model.Member;

public class MimeMessageTest implements SendMailService {
	private JavaMailSender mailSender;
	 
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    @Override
    public void sendMail(Member member) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setSubject("[공지] 회원 가입 안내", "UTF-8");
            String htmlContent = "<strong>안녕하세요</strong>, 반갑습니다.";
            message.setText(htmlContent, "UTF-8", "html");
            message.setFrom(new InternetAddress("gz.kyungho@gmail.com"));
            message.addRecipient(RecipientType.TO, new InternetAddress(member.getEmail()));
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        } catch (MailException e) {
            e.printStackTrace();
            return;
        } // try - catch
    }
}
