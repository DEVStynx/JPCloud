package de.stynxyxy.jpcloudbackend.service.db.session;

import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionEntity;
import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class SessionService {
    @Autowired
    SessionRepository repository;
    private static Logger logger = Logger.getLogger(SessionService.class.getName());

    public String createSession(String ipadress) {
        if (repository.existsByIp(ipadress)) {
            return "could not register new User because this Ip is already registered!";
        } else {
            System.out.println("doesn't exist");
        }
        UUID token = UUID.randomUUID();
        SessionEntity entity = new SessionEntity(token,ipadress, LocalDateTime.now());
        repository.save(entity);

        logger.info("created new Token for Ipaddress: "+ipadress+ " Token: "+token);

        return entity.getToken().toString();
    }
    public SessionEntity findByToken(UUID token) {
        return repository.findByToken(token);
    }
    public void removeByToken(UUID token) {
        repository.removeByToken(token);
    }
    public void removeByIp(String ip) {
        repository.removeByIp(ip);
    }

}
