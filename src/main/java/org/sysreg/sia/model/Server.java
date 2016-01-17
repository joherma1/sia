package org.sysreg.sia.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by joseant on 22/07/15.
 */
@Entity
@Table(name = "SERVERS")
public class Server {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String ip;

    @Column
    private int port;

    @Column
    private String username;

    @Column
    private String password;
    
    @Column
    private String description;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TOWN_ID", referencedColumnName = "TOWN_ID"),
            @JoinColumn(name = "AGGREGATE", referencedColumnName = "AGGREGATE"),
            @JoinColumn(name = "ZONE", referencedColumnName = "ZONE"),
            @JoinColumn(name = "POLYGON", referencedColumnName = "POLYGON"),
            @JoinColumn(name = "PARCEL", referencedColumnName = "PARCEL"),
            @JoinColumn(name = "ENCLOSURE", referencedColumnName = "ENCLOSURE"),
    })
    private Enclosure enclosure;

    @OneToMany(mappedBy = "server", fetch = FetchType.EAGER)
    private Set<Board> boards = new HashSet<>();

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Server() {
    }

    public Server(String ip, int port, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Server(String ip, int port, String username, String password, String description) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.description = description;
    }
}
