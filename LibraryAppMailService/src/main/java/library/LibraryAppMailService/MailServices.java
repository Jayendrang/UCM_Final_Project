package library.LibraryAppMailService;

import java.io.IOException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

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
@RequestMapping("/library/mailservice")
public class MailServices {

	@Autowired
	JavaMailSender mailSender;

	@PostMapping("/sendmail")
	public MailResponse sendMail(@Valid @RequestBody MailDetails mailData) throws IOException {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.setContent(
					messageBody(mailData.getUserid(), mailData.getPassword(), mailData.getRole()).toString(),
					"text/html");
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, false, "utf-8");
			messageHelper.setTo(mailData.getMailid());
			messageHelper.setSubject("KMax account activated!");

			mailSender.send(message);
			return new MailResponse(mailData.getMailid(), "Success".toUpperCase());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new MailResponse(mailData.getMailid(), "Failed".toUpperCase());
	}

	public String messageBody(String username, String password, String role) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<h3>Welcome,</h3>");
		if (role.equalsIgnoreCase("GN")) {
			builder.append(
					"<p> Your user account was created successfully. Please use the below credentials to login to KMax portal. Also, reset your password with 24 hours before your account gets locked.</p>");
		} else if (role.equalsIgnoreCase("LIB")) {
			builder.append(
					"<p> Your librarian account was created successfully. Please use the below credentials to login to KMax portal. Also, reset your recovery questions, password with 24 hours before your account gets locked.</p>");

		} else if (role.equalsIgnoreCase("ADM")) {
			builder.append(
					"<p> Your administrator account was created successfully. Please use the below credentials to login to KMax portal. Also, reset your password with 24 hours before your account gets locked.</p>");

		}
		builder.append("<h4>Username :</h4>  " + username);
		builder.append("<h4>Password :</h4>\n" + password);
		builder.append("<p>Thanks and regards,</p>\n" + "\n" + "<h3> Team KMax </h3> \n" + "</html>");
		return builder.toString();
	}
}
