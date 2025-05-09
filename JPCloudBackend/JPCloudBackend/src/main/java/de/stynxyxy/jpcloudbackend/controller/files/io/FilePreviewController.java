package de.stynxyxy.jpcloudbackend.controller.files.io;

import de.stynxyxy.jpcloudbackend.Main;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import de.stynxyxy.jpcloudbackend.service.files.FilestorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class FilePreviewController {
    @Autowired
    FilestorageService filestorageService;
    @Autowired
    SessionValidationService sessionValidationService;

    @GetMapping("/preview")
    public ResponseEntity<String> preview(@RequestParam(name = "token") String token, String filePath) {
        if (!sessionValidationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.status(403).build();
        }
        File file = new File(FilestorageService.getRootDirectory() + "/"+filePath);
        System.out.println(file.getAbsolutePath());
        if (!file.exists() || file.isDirectory()) {
            return ResponseEntity.of(Optional.of(file.exists() ? "The given File is a Directory" : "This File doesnt exist"));
        }
        StringBuilder preview = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().forEach(preview::append);


        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).info("An Error Occured while getting Preview: "+e.getMessage()+ "\n "+ e.getStackTrace());
        }
        return ResponseEntity.of(Optional.of(preview.toString()));
    }
}
