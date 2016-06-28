package models.config;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;
import com.fasterxml.jackson.xml.annotate.JacksonXmlRootElement;

/**
 * Created by zipfs on 2016. 06. 28..
 */
@JacksonXmlRootElement(localName = "GameServerConfig")
public class DatabaseConfig {

    @JacksonXmlProperty(isAttribute = true)
    private String databaseURL;

    public String getDatabaseURL() {
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }
}
