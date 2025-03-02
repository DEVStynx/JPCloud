package de.stynxyxy.jpcloudbackend.service.db;

import de.stynxyxy.jpcloudbackend.db.model.user.UserEntity;
import de.stynxyxy.jpcloudbackend.db.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public boolean registerUser(String username, String password, boolean perm_write, boolean perm_read) {
        UserEntity userEntity = new UserEntity(username,password,perm_write,perm_read);
        try {
            repository.save(userEntity);
            return true;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }

    public UserEntity findByUserName(String username) {
        return repository.findByUsername(username);
    }
}
