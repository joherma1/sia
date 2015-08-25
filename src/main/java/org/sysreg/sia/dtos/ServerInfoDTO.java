package org.sysreg.sia.dtos;

/**
 * Created by joseant on 25/08/15.
 */
public class ServerInfoDTO {
    private String host;

    private int port;

    private String description;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
