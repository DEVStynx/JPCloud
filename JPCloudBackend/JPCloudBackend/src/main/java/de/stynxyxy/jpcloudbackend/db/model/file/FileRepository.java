package de.stynxyxy.jpcloudbackend.db.model.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface FileRepository extends JpaRepository<FileEntity,Long> {
    public FileEntity findByName(String name);
}
