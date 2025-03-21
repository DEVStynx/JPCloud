package de.stynxyxy.jpcloudbackend.controller.files.management;

import de.stynxyxy.jpcloudbackend.model.FileInformation;
import de.stynxyxy.jpcloudbackend.service.files.FileGetterService;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.UUID;

@RestController
public class FileInformationController {
    @Autowired
    FileGetterService fileGetterService;
    @Autowired
    SessionValidationService validationService;

    @GetMapping("/files/list")
    public FileInformation[] find(@RequestParam(name = "path") String path, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return null;
        }
        try {
            return fileGetterService.getFilesAtPath(path);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    @GetMapping("/file/info")
    public ResponseEntity<FileInformation> getInfo(@RequestParam(name = "path") String path, @RequestParam(name ="token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.status(403).build();
        }
        try {
            return ResponseEntity.ok().body(fileGetterService.getInformationOfFile(path));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
