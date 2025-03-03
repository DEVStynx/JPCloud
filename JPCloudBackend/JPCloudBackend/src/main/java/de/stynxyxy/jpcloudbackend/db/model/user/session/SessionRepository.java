package de.stynxyxy.jpcloudbackend.db.model.user.session;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    public SessionEntity findById(long id);
    public SessionEntity findByToken(UUID token);

    SessionEntity findByUsername(String username);
    @Transactional
    void removeByToken(UUID token);
    @Transactional
    boolean existsByUsername(String username);
    @Transactional
    void removeByIp(String ip);
    @Transactional
    boolean existsByIp(String ip);

    String ip(String ip);
}
