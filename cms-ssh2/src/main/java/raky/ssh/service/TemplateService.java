package raky.ssh.service;

import java.util.Map;

public interface TemplateService {

	String getContent(String templateName, Map<String, Object> model);

}
