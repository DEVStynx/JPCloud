package de.stynxyxy.jpcloudbackend.controller.files;

import de.stynxyxy.jpcloudbackend.service.FilestorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
public class FileDownloadController {
    @Autowired
    FilestorageService filestorageService;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("path")String path) {
        File targetFile = new File(FilestorageService.getRootDirectory() + "\\" + path);
        try {
            return filestorageService.download2(targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
