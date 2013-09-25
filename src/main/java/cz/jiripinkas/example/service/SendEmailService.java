package cz.jiripinkas.example.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

	public static void sendEmail(String from, String to, String subject, String body) throws MessagingException {

		Properties props = System.getProperties();

		Properties emailProperties = new Properties();
		try {
			emailProperties.load(SendEmailService.class.getResourceAsStream("/email.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new MessagingException("could not read config properties");
		}

		String smtp = emailProperties.getProperty("mail.smtp");
		String username = emailProperties.getProperty("mail.username");
		String password = emailProperties.getProperty("mail.password");
		String port = emailProperties.getProperty("mail.port");
		boolean preview = Boolean.parseBoolean(emailProperties.getProperty("preview"));

		if (!preview) {
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.smtp.host", smtp);
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.user", username);

			final Session session = Session.getInstance(props, null);
			session.setPasswordAuthentication(new URLName("smtp", smtp, -1, null, username, null), new PasswordAuthentication(username, password));

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
		}
	}
}
