package de.stynxyxy.jpcloudbackend.db.model.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(
            name = "username",
            unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="perm_write")
    private boolean write;

    @Column(name = "perm_read")
    private boolean read;

    public UserEntity( String username, String password, boolean write, boolean read) {
        this.username = username;
        this.password = password;
        this.write = write;
        this.read = read;
    }
    public UserEntity() {}

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
