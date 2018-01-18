package raky.ssh.service.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import raky.ssh.service.MailService;
import raky.ssh.service.TemplateService;

public class MailServiceDefaultImpl implements MailService {

	private final Logger logger = Logger.getLogger(this.getClass());

	private JavaMailSender mailSender;

	private TemplateService templateService;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void send(SimpleMailMessage mailMessage) {
		mailSender.send(mailMessage);
	}

	public void send(SimpleMailMessage msg, String templateName, Map<String, Object> model) {
		String content = templateService.getContent(templateName, model);
		logger.info("-----------------" + content);
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
			helper.setTo(msg.getTo());
			helper.setSubject(msg.getSubject());
			helper.setFrom(msg.getFrom());
			helper.setText(content, true);
			mailSender.send(mimeMsg);
		} catch (MessagingException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
