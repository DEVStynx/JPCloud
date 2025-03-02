package de.stynxyxy.jpcloudbackend.controller.files.db;

import de.stynxyxy.jpcloudbackend.service.db.files.FileDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDbDownloadController {
    @Autowired
    FileDatabaseService fileDatabaseService;

    @GetMapping("/file/db/download")
    public ResponseEntity<Resource> download(@RequestParam("filename") String filename) {
        return fileDatabaseService.download(filename);
    }

}
