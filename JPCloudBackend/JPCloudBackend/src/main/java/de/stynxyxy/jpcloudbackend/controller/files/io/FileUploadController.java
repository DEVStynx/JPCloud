package de.stynxyxy.jpcloudbackend.controller.files.io;

import de.stynxyxy.jpcloudbackend.service.files.FilestorageService;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileUploadController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    FilestorageService service;
    @Autowired
    SessionValidationService validationService;
    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class.getName());

    @PostMapping("/upload")
    public boolean upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            System.out.println("Got called again!");
            return false;
        }
        System.out.println("Got so far");
        try {
            System.out.println("trying");
            service.saveFile(file);
            LOGGER.info("Downloaded new File: "+file.getOriginalFilename());
            return true;
        } catch (IOException e) {
            System.out.println("DIdn't work");
            LOGGER.log(Level.SEVERE,"Exception during file Upload!",e);
        }
        return false;
    }
}
