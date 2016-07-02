package models.config;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

/**
 * Created by zipfs on 2016. 06. 28..
 */
public class MailConfig {

    @JacksonXmlProperty(localName = "MailFrom")
    private String mailFrom;
    @JacksonXmlProperty(localName = "MailSender")
    private String mailSender;

}
