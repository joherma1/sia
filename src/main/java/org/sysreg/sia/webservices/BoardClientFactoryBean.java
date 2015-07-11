package org.sysreg.sia.webservices;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by joseant on 11/07/15.
 */
public class BoardClientFactoryBean extends AbstractFactoryBean<Object> {

    private String url;

    private int port;

    @Resource(name = "springWSClient")
    private RestTemplate restTemplate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //This method is required for autowiring to work correctly
    @Override
    public Class<?> getObjectType() {
        return BoardClient.class;
    }

    //This method will be called by container to create new instances
    @Override
    protected Object createInstance() throws Exception {
        BoardClient boardClient =  new BoardClient();
        boardClient.setUrl(this.url);
        boardClient.setPort(this.port);
        boardClient.setRestTemplate(this.restTemplate);
        return boardClient;
    }
}
