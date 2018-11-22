package library.LibraryAppMailService;

import java.io.IOException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.pojo.MailDetails;
import library.pojo.MailResponse;

@RestController
@RequestMapping("/mailservice")
public class MailServices {

	@Autowired
	JavaMailSender mailSender;

	@PostMapping("/sendmail")
	public MailResponse sendMail(@RequestBody MailDetails mailData)throws IOException {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.setContent(messageBody(mailData.getUsername(), mailData.getPassword()).toString(), "text/html");
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,false,"utf-8");
			messageHelper.setTo(mailData.getReciever());
			messageHelper.setSubject("KMax Librarian account activated!");
			
			mailSender.send(message);
			return new MailResponse(mailData.getReciever(), "Successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new MailResponse(mailData.getReciever(), "Failed");
	}

	
	public String messageBody(String username,String password) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<h3>Welcome,</h3>");
		builder.append("<p> Your librarian account was created successfully. Please use the below credentials to login to KMax portal. Also, reset your password with 24 hours before your account gets locked.</p>");
		builder.append("<h4>Username :</h4>  "+username);
		builder.append("<h4>Password :</h4>\n"+password);
		builder.append("<p>Thanks and regards,</p>\n" + 
				"\n" + 
				"<h3> Team KMax </h3> \n" + 
				"</html>");
		return builder.toString();
	}
}
