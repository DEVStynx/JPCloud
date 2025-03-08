package de.stynxyxy.jpcloudbackend.controller.files.io;

import de.stynxyxy.jpcloudbackend.service.files.FilestorageService;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileDownloadController {
    @Autowired
    FilestorageService filestorageService;
    @Autowired
    SessionValidationService validationService;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("path")String path, @RequestParam("token") String token) {
        if (validationService.RemoveCheckValidationOfSession(UUID.fromString(token)) == false) {
            return ResponseEntity.status(403).build();
        }
        File targetFile = new File(FilestorageService.getRootDirectory() + "\\" + path);
        try {
            return filestorageService.download2(targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
