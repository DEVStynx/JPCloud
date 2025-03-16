package de.stynxyxy.jpcloudbackend.service.db.session;

import de.stynxyxy.jpcloudbackend.db.model.user.UserEntity;
import de.stynxyxy.jpcloudbackend.db.model.user.UserRepository;
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
    @Autowired
    UserRepository userRepository;

    private static Logger logger = Logger.getLogger(SessionService.class.getName());

    public String createSession(String ipadress, String username, String password) {
        //Check if a Session is already existing for the User
        UserEntity user = userRepository.findByUsername(username);
        //Check credentials of the User
        if (user == null) {
            return "Err Invalid Username!";
        }
        if (!user.getPassword().equals(password)) {
            return "Err invalid Password!";
        }
        //Does a Session already exist if invalid remove
        if (repository.existsByUsername(username)) {
            SessionEntity sessionEntity = repository.findByUsername(username);
            if (sessionEntity.isStillvalid()) {
                return "Err Session already exists for Username!";
            } else {
                repository.removeByToken(sessionEntity.getToken());
            }

        }
        //Check if the current ip is already taken by another session
        if (repository.existsByIp(ipadress)) {
            return "Err could not register new User because this Ip is already registered!";
        } else {
            System.out.println("doesn't exist");
        }
        //generare Token
        UUID token = UUID.randomUUID();
        SessionEntity entity = new SessionEntity(token,ipadress, LocalDateTime.now(), username);

        repository.save(entity);

        logger.info("created new Token for Ipaddress: "+ipadress+ " Token: "+token);

        return entity.getToken().toString();
    }

    //Database Methods
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
