package de.stynxyxy.jpcloudbackend.controller.files.db;

import de.stynxyxy.jpcloudbackend.service.db.files.FileDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class FileDbUploadController {
    @Autowired
    FileDatabaseService fileDatabaseService;

    public static Logger LOGGER = Logger.getLogger(FileDbUploadController.class.getName());

    @GetMapping("file/db/upload")
    public boolean upload(@RequestParam(name = "path") String path) {
        try {
            return fileDatabaseService.store(path);
        } catch (IOException e) {
            LOGGER.warning("Exception: "+e.getMessage());
            return false;
        }
    }
}
