package de.stynxyxy.jpclouddevelopment.controller.io;

import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import de.stynxyxy.jpclouddevelopment.service.IO.filestream.FileIOService;
import de.stynxyxy.jpclouddevelopment.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;

@RestController
public class FileIOController {
    @Autowired
    private FileIOService fileIOService;

    @PostMapping("/file/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, String path) {
        StoredCloudFile cloudFile = FileUtil.MultipartToData(file,path);
        fileIOService.uploadFile(file,cloudFile);

        String redirectTo = "/dashboard";
        if (path != null && !path.isEmpty()) {
            redirectTo += "?path=" + path;
        } else {
            redirectTo += "?path=";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",redirectTo);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
    @GetMapping("/file/download")
    public ResponseEntity<?> download(@RequestParam("path") String path) {
        File file = new File(path);
        StoredCloudFile storedCloudFile = new StoredCloudFile(file.getName(),path,file,0,file.getName());
        return fileIOService.downloadFile(storedCloudFile);
    }



}
