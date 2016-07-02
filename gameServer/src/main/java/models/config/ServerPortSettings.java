package models.config;

import com.fasterxml.jackson.xml.annotate.JacksonXmlProperty;

/**
 * Created by zipfs on 2016. 06. 28..
 */
public class ServerPortSettings {

    @JacksonXmlProperty(localName = "UDPPort")
    private int udpPort;
    @JacksonXmlProperty(localName = "TCPPort")
    private int tcpPort;

}
