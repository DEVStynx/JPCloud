package de.stynxyxy.jpcloudbackend.service;

import de.stynxyxy.jpcloudbackend.Main;
import de.stynxyxy.jpcloudbackend.model.FileInformation;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class FileGetterService {

    public FileInformation[] getFilesAtPath(String path) throws FileNotFoundException {
        File file = new File(Main.sourcePath + File.separator+path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (!file.isDirectory()) {
            FileInformation[] fileInformations = new FileInformation[1];
            fileInformations[0] = new FileInformation(file.getName(),file.getTotalSpace(),file.getName(),path,!file.isFile());
            return fileInformations;
        }
        FileInformation[] fileInformations = new FileInformation[file.listFiles().length];
        AtomicInteger index = new AtomicInteger(0);

        Arrays.stream(file.listFiles()).forEach(cfile -> {
            fileInformations[index.getAndIncrement()] = new FileInformation(
                    cfile.getName(),
                    cfile.getTotalSpace(),
                    cfile.getName(),
                    path + File.separator + cfile.getName(),
                    !cfile.isFile()
            );
        });

        return fileInformations;
    }
}
