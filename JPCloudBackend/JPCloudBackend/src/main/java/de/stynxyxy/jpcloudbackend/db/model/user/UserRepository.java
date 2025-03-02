package de.stynxyxy.jpcloudbackend.db.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByUsername(String username);

}
