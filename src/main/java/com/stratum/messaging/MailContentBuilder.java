package com.stratum.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {
 
    private TemplateEngine templateEngine;
 
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String build(String header, String value) {
        Context context = new Context();
       context.setVariable("header", header);
       context.setVariable("value", value);
        return templateEngine.process("mailTemplate", context);
    }
 
}