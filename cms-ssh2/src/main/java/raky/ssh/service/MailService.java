package raky.ssh.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

	public void send(SimpleMailMessage msg);

	public void send(SimpleMailMessage msg, String templateName, Map<String, Object> model);

}
