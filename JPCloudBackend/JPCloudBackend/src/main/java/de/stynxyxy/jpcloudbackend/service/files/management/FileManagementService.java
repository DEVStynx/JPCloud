package de.stynxyxy.jpcloudbackend.service.files.management;


import de.stynxyxy.jpcloudbackend.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import static java.rmi.server.LogStream.log;

@Slf4j
@Service
public class FileManagementService {
    /**
     *
     * @param source The Source File path
     * @param dest The Destination <p>Can be a {@link File File} or a Directory</p>
     * @return  <p>A{@link ResponseEntity Responsentity}</p> for REST-API
     *
     * @implNote <p>If an existing {@link File File} is given, it will be replaced with the new one</p>
     */
    public ResponseEntity<Boolean> MoveFile(File source, File dest) {

        if (!source.exists()) {
            return ResponseEntity.badRequest().body(false);
        }
        try {
            if (dest.isDirectory()) {
                Files.move(source.toPath(),dest.toPath());
                return ResponseEntity.ok(true);
            }
            Files.move(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).body(false);
        }
        return ResponseEntity.of(Optional.of(false));
    }
    /**
     *
     * @param source The Source File path
     * @param dest The Destination <p>Can be a {@link File File} or a Directory</p>
     * @return  <p>A{@link ResponseEntity Responsentity}</p> for REST-API
     *
     * @implNote
     * If an existing {@link File File} is given, it will be replaced with the new one
     */
    public ResponseEntity<Boolean> CopyFile(File source, File dest) {
        if (!source.exists()) {
            return ResponseEntity.badRequest().eTag("The source doesn't exist").body(false);
        }
        try {
            if (dest.exists() && dest.isDirectory()) {
                Files.copy(source.toPath(),dest.toPath());
                return ResponseEntity.ok(true);
            }
            Files.copy(source.toPath(),dest.toPath(),StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).body(false);
        }
    }
    /**
     *
     * @param source The Source File path
     * @param ptitle The new Title
     * @return  <p>A{@link ResponseEntity Responsentity}for REST-API</p>
     * @implNote </p>Add the {@link String File Extension} to the new {@link String File name}</p>
     */
    public ResponseEntity<Boolean> RenameFile(File source, String ptitle) {
        if (!source.exists()) {
            return ResponseEntity.
                    badRequest().
                    eTag("doesn't exist").
                    body(false);
        }
        try {
            File renamedFile = new File(source.getAbsolutePath().replace(source.getName(),ptitle));
            source.renameTo(renamedFile);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.
                    badRequest().
                    eTag(e.getMessage()).
                    body(false);
        }

    }
    /**
     *
     * @param target The Target {@link File File} path
     * @return  <p>A{@link ResponseEntity Responsentity}</p> for REST-API
     */
    public ResponseEntity<Boolean> deleteFile(File target) {
        if (!target.exists()) {
            return ResponseEntity.
                    badRequest().
                    eTag("This File doesn't exist!").
                    body(false);
        }

        return ResponseEntity.
                ok(target.delete());
    }

    public void createDirectory(String path, String name) {
        File directoryBaseDir = new File(Main.sourcePath + File.separator+path);
        if (!directoryBaseDir.exists()) {
            System.err.println("This Path doesn't exist! " + directoryBaseDir);
            return;
        }

        directoryBaseDir = new File(directoryBaseDir.getAbsolutePath() + File.separator + name + File.separator);
        if (directoryBaseDir.exists()) {
            System.err.println("The given Directory does exist or isn't a Directory! " + directoryBaseDir);
            System.err.println("exist: "+directoryBaseDir.exists());
            System.err.println("dir " + !directoryBaseDir.isDirectory());
            return;
        }
        directoryBaseDir.mkdirs();
    }
}
