package de.stynxyxy.jpcloudbackend.controller.files;

import de.stynxyxy.jpcloudbackend.service.FilestorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class FileUploadController {
    @Autowired
    FilestorageService service;
    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class.getName());

    @PostMapping("/upload")
    public boolean upload(@RequestParam("file") MultipartFile file) {
        try {
            service.saveFile(file);
            LOGGER.info("Downloaded new File: "+file.getOriginalFilename());
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Exception during file Upload!",e);
        }
        return false;
    }
}
