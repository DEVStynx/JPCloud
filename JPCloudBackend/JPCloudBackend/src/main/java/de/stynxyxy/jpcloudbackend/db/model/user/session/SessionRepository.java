package de.stynxyxy.jpcloudbackend.db.model.user.session;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.UUID;


@RepositoryRestResource(exported = false)
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
