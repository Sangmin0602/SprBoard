package spr.board.utils.mail;

import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import spr.board.model.Member;

public class MimeMessageInlineTest implements SendMailService {
	private JavaMailSender mailSender;
	 
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    @Override
    public void sendMail(Member member) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject("[공지] 회원 가입 안내");
            //String htmlContent = "<strong>안녕하세요</strong>, 반갑습니다." + "<img src="cid:abc">";
            //messageHelper.setText(htmlContent, true);
            messageHelper.setFrom("gz.kyungho@gmail.com", "갱짱");
            messageHelper.setTo(new InternetAddress(member.getEmail(), member.getName(), "UTF-8"));
            messageHelper.addInline("abc", new FileDataSource("abc.jpg"));
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
