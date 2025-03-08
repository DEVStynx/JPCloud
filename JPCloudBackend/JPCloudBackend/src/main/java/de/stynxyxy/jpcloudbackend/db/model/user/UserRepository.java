package de.stynxyxy.jpcloudbackend.db.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByUsername(String username);

}
