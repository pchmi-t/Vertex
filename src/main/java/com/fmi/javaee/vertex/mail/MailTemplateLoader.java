package com.fmi.javaee.vertex.mail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.inject.Inject;

public class MailTemplateLoader {

	private final VelocityEngine velocityEngine;
	private final String templateFolder;

	@Inject
	public MailTemplateLoader(@MailTemplateFolder String templateFolder, VelocityEngine velocityEngine) {
		this.templateFolder = templateFolder;
		this.velocityEngine = velocityEngine;
	}

	public String render(String templateFile, Map<String, String> templateContext) throws MailTemplateException {
		String template = loadTemplate(templateFile);

		VelocityContext context = new VelocityContext(templateContext);
		StringWriter writer = new StringWriter();
		velocityEngine.evaluate(context, writer, "TemplateName", template);
		return writer.toString();
	}

	private String loadTemplate(String templateFile) throws MailTemplateException {
		URL templateURL = MailTemplateLoader.class.getResource(templateFolder + templateFile);
		try {
			return Files.toString(new File(templateURL.toURI()), Charsets.UTF_8);
		} catch (IOException | URISyntaxException e) {
			throw new MailTemplateException("Could not load mail template: " + templateFile, e);
		}
	}

}
