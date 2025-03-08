package de.stynxyxy.jpcloudbackend.controller.files.management;


import de.stynxyxy.jpcloudbackend.Main;
import de.stynxyxy.jpcloudbackend.service.db.session.SessionValidationService;
import de.stynxyxy.jpcloudbackend.service.files.management.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.UUID;

@RestController
public class FileManagementController {
    @Autowired
    SessionValidationService validationService;
    @Autowired
    FileManagementService managementService;

    @GetMapping("/file/copy")
    public ResponseEntity<Boolean> copy(@RequestParam(name = "source") String source, @RequestParam(name = "target") String target, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.
                    badRequest().
                    eTag("Invalid Token").
                    body(false);
        }
        File sourcepath = new File(Main.sourcePath + File.separator + source);
        File targetpath = new File(Main.sourcePath + File.separator + target);

        return managementService.CopyFile(sourcepath,targetpath);
    }

    @GetMapping("/file/move")
    public ResponseEntity<Boolean> move(@RequestParam(name = "source") String source, @RequestParam(name = "target") String target, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.
                    badRequest().
                    eTag("Invalid Token").
                    body(false);
        }
        File sourcepath = new File(Main.sourcePath + File.separator + source);
        File targetpath = new File(Main.sourcePath + File.separator + target);

        return managementService.MoveFile(sourcepath,targetpath);
    }
    @GetMapping("/file/rename")
    public ResponseEntity<Boolean> rename(@RequestParam(name = "source") String source, @RequestParam(name = "name") String name, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.
                    badRequest().
                    eTag("Invalid Token").
                    body(false);
        }
        File sourcepath = new File(Main.sourcePath + File.separator + source);

        return managementService.RenameFile(sourcepath,name);
    }
    @GetMapping("/file/delete")
    public ResponseEntity<Boolean> delete(@RequestParam(name = "target") String target, @RequestParam(name = "token") String token) {
        if (!validationService.RemoveCheckValidationOfSession(UUID.fromString(token))) {
            return ResponseEntity.
                    badRequest().
                    eTag("Invalid Token").
                    body(false);
        }

        File targetpath = new File(Main.sourcePath+File.separator+target);
        return managementService.deleteFile(targetpath);
    }
}
