package de.stynxyxy.jpcloudbackend.service.db.files;

import de.stynxyxy.jpcloudbackend.Main;
import de.stynxyxy.jpcloudbackend.db.model.file.FileEntity;
import de.stynxyxy.jpcloudbackend.db.model.file.FileRepository;
import de.stynxyxy.jpcloudbackend.service.files.FilestorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Logger;

@Service("fileDatabaseService")
public class FileDatabaseService {

    @Autowired
    FileRepository repository;

    private static Logger LOGGER = Logger.getLogger(FilestorageService.class.getName());

    public boolean store(String path) throws IOException {
        File targetFile = new File(Main.sourcePath+ File.separator + path);
        if (!targetFile.exists()) {
            LOGGER.warning("This File doesn't exist!");
            return false;
        }
        FileEntity fileEntity = new FileEntity(Files.newInputStream(targetFile.toPath()).readAllBytes(),targetFile.getName(),path);
        repository.save(fileEntity);
        return true;
    }

    public ResponseEntity<Resource> download(String name) {
        FileEntity targetEntity = repository.findByName(name);
        File tempStorage = new File(Main.sourcePath+File.separator+"db.temp"+File.separator+name);
        ByteArrayResource s = new ByteArrayResource(targetEntity.getFilecontent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + targetEntity.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(s);

    }
}
