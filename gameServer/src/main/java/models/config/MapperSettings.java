package models.config;

import com.fasterxml.jackson.xml.annotate.JacksonXmlElementWrapper;
import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;
import com.fasterxml.jackson.xml.annotate.JacksonXmlRootElement;

import java.util.List;

/**
 * Created by zipfs on 2016. 06. 28..
 */

public class MapperSettings {


    @JacksonXmlElementWrapper
    @JacksonXmlProperty(localName = "RequestMap")
    private RequestMap[] requestMaps;

    public MapperSettings(RequestMap[] requestMaps) {
        this.requestMaps = requestMaps;
    }

    public MapperSettings() {
    }

    public RequestMap[] getRequestMaps() {
        return requestMaps;
    }

    public void setRequestMaps(RequestMap[] requestMaps) {
        this.requestMaps = requestMaps;
    }
}
