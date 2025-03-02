package de.stynxyxy.jpcloudbackend.db.model.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    public FileEntity findByName(String name);
}
