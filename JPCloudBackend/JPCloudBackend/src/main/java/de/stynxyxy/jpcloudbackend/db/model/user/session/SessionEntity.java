package de.stynxyxy.jpcloudbackend.db.model.user.session;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class SessionEntity {
    public static int SessionValidationTime= 1;


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private long id;

    @Getter
    @Column(name = "ip",
    unique = true,
    nullable = false)
    private String ip;

    @Column(name="token",unique = true,nullable = false)
    @Getter
    private UUID token;

    @Column(name = "username", unique = true, nullable = false)
    @Getter
    private String username;

    @Column(name = "timecreated")
    @Getter
    private LocalDateTime createdTime;

    public SessionEntity() {}

    public SessionEntity(UUID token, String ipAdress, LocalDateTime created, String Username) {
        this.token = token;
        this.username = Username;
        ip = ipAdress;
        createdTime = created;
    }
    public boolean isStillvalid() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiary = getCreatedTime().plusMinutes(SessionValidationTime);
        if (now.isAfter(expiary)) {
            return false;
        }
        return true;
    }

}
