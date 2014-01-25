package org.sysreg.sia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jose on 22/01/14.
 */
@Entity
@Table(name="AUTHORITIES")
public class Authority implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @Column (unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "authority")
    private Set<User> users = new HashSet<User>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
