package de.stynxyxy.jpcloudbackend.db.model.structure;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SideFolderRepository extends CrudRepository<SideFolder, Long> {
    SideFolder findById(long id);
    SideFolder findByLabel(String label);
    void removeById(long id);

    void removeByLabel(String label);
    void removeByPath(String path);
}
