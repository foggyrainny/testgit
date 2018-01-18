package raky.ssh.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import raky.ssh.service.TemplateService;

public class TemplateServiceFreeMarkerImpl implements TemplateService {

	private final Logger logger = Logger.getLogger(this.getClass());

	private FreeMarkerConfigurer freeMarkerConfigurer;

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public String getContent(String templateName, Map<String, Object> model) {
		try {
			Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			logger.debug(FreeMarkerTemplateUtils.processTemplateIntoString(t, model));
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		} catch (TemplateException e) {
			logger.error("Error while processing FreeMarker template ", e);
		} catch (FileNotFoundException e) {
			logger.error("Error while open template file ", e);
		} catch (IOException e) {
			logger.error("IO Exception ", e);
		}
		return null;
	}
}
