package de.stynxyxy.jpcloudbackend.controller.files;

import de.stynxyxy.jpcloudbackend.model.FileInformation;
import de.stynxyxy.jpcloudbackend.service.FileGetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class FileInformationController {
    @Autowired
    FileGetterService fileGetterService;

    @GetMapping("/files/list")
    public FileInformation[] find(@RequestParam(name = "path") String path) {
        try {
            return fileGetterService.getFilesAtPath(path);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
