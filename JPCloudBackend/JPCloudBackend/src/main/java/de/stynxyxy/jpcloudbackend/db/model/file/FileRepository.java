package de.stynxyxy.jpcloudbackend.db.model.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(exported = false)
public interface FileRepository extends JpaRepository<FileEntity,Long> {
    public FileEntity findByName(String name);
}
