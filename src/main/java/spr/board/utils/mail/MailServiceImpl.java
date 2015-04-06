package spr.board.utils.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import spr.board.model.Member;

@Service
public class MailServiceImpl implements SendMailService{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

//    public void sendMail(String subject, String text, String fromUser, String toUser, String[] toCC) {
//        MimeMessage message = mailSender.createMimeMessage();
//
//        try {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//            messageHelper.setSubject(subject);
//            messageHelper.setTo(toUser);
//            messageHelper.setCc(toCC);
//            messageHelper.setFrom(fromUser);
//            messageHelper.setText(text, true);
//            mailSender.send(message);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

	@Override
	public void sendMail(Member member) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject("stetest");
            messageHelper.setCc("gz.kyungho@gmail.com");
            messageHelper.setFrom("gz.kyungho@gmail.com", "갱짱");
            messageHelper.setTo(new InternetAddress(member.getEmail(), member.getName(), "UTF-8"));
            messageHelper.setText("testset", true);
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return;
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
		
	}
}
