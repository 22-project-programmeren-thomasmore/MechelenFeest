package be.thomasmore.screeninfo.model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String from, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setFrom(from);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendConfirmationEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendVerificationEmail(EndUser user, VerificationToken token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmailAddress());
        message.setSubject("Complete Registration!");
        message.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account/"+token.getToken());
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(EndUser user, VerificationToken token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmailAddress());
        message.setSubject("Reset Password");
        message.setText("To reset your password, please click here : "
                +"http://localhost:8080/new-password/"+token.getToken());
        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String text) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/files/Klaar.pdf"));
        helper.addAttachment("MechelenFeest.pdf", file);

        mailSender.send(message);
    }
}
