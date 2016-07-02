package models.config;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

/**
 * Created by zipfs on 2016. 06. 28..
 */
public class LoggerSettings {

    @JacksonXmlProperty(isAttribute = true)
    private String sendEmail;
    @JacksonXmlProperty(isAttribute = true)
    private String[] write;
}
