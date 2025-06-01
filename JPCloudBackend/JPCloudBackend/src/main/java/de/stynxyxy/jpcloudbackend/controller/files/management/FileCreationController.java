package de.stynxyxy.jpcloudbackend.controller.files.management;

import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import de.stynxyxy.jpcloudbackend.service.files.management.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FileCreationController {
    @Autowired
    FileManagementService fileManagementService;
    @Autowired
    SessionValidationService sessionValidationService;

    @PostMapping("/files/createFolder")
    public ResponseEntity<Void> createFolder(@RequestParam(name = "token") String token, @RequestParam(name="path") String path, @RequestParam(name="name") String name) {
        if (!sessionValidationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.status(403).build();
        }

        fileManagementService.createDirectory(path, name);
        return ResponseEntity.ok().build();
    }
}
