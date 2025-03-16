package de.stynxyxy.jpcloudbackend.controller.files;

import de.stynxyxy.jpcloudbackend.model.FileInformation;
import de.stynxyxy.jpcloudbackend.service.files.FileGetterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;


@RestController
public class WebMainPageController {

    @Autowired
    private FileGetterService fileGetterService;

    @GetMapping("/dashboard")
    public ResponseEntity<FileInformation[]> loadMainpage(HttpServletRequest request, @RequestParam(name = "token") String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/main.html?token=" + token+"&path=/");
        FileInformation[] files;
        try {
            files = fileGetterService.getFilesAtPath("/");
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(302).headers(headers).body(files);
    }
    @GetMapping("/dashboard/path")
    public ResponseEntity<FileInformation[]> loadPath(HttpServletRequest request, @RequestParam(name = "token") String token, @RequestParam(name = "path") String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/main.html?token=" + token+"&path=/");
        FileInformation[] files;
        try {
            files = fileGetterService.getFilesAtPath(path);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(302).headers(headers).body(files);
    }
}
