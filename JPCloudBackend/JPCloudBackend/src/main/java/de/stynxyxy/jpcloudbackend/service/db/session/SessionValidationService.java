package de.stynxyxy.jpcloudbackend.service.db.session;


import de.stynxyxy.jpcloudbackend.db.model.user.UserRepository;
import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionEntity;
import de.stynxyxy.jpcloudbackend.db.model.user.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class SessionValidationService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(SessionValidationService.class.getName());

    public boolean checkValidationOfSession(UUID Token) {
        //Check the Existance of Session
        SessionEntity session = sessionRepository.findByToken(Token);
        if (session == null) {
            return false;
        }
        return session.isStillvalid();
    }
    public boolean RemoveCheckValidationOfSession(UUID Token) {
        boolean valid = checkValidationOfSession(Token);
        logger.info("token: "+Token + " valid: "+valid);
        if (valid == true) {
            return valid;
        }
        sessionRepository.removeByToken(Token);
        return valid;
    }

}
