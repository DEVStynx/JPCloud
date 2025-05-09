package de.stynxyxy.jpcloudbackend.service.files;

import de.stynxyxy.jpcloudbackend.Main;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class FilestorageService {

    private static final Logger LOGGER = Logger.getLogger(FilestorageService.class.getName());

    public void saveFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        String filename = file.getOriginalFilename();
        if (filename.contains("\"")) {
            filename = filename.replace('\"','\0');
        }
        Path targetFile = Path.of(Main.sourcePath + File.separator + file.getOriginalFilename());

        Files.copy(file.getInputStream(),targetFile,StandardCopyOption.REPLACE_EXISTING);
    }


    public static String getRootDirectory() {
        return Main.sourcePath;
    }
    public ResponseEntity<Resource> download(File path) throws IOException {
        LOGGER.info("Called!");
        if (!path.exists() && !path.isDirectory()) {
            LOGGER.info("This format is not supported");
            LOGGER.info("Path: " + path.toString());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(new InputStreamResource(Files.newInputStream(path.toPath())));
    }

}
