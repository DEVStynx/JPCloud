package de.stynxyxy.jpcloudbackend.service.files;

import de.stynxyxy.jpcloudbackend.Main;
import de.stynxyxy.jpcloudbackend.model.FileInformation;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class FileGetterService {
    private static Logger logger = Logger.getLogger(FileGetterService.class.getName());

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

    public FileInformation getInformationOfFile(String path) throws FileNotFoundException {
        File file = new File(Main.sourcePath + File.separator + path);
        if (file.exists()) {
            String filename = file.getName();
            long size = file.getTotalSpace();
            boolean dir = file.isDirectory();
            logger.info("path: "+path+" is dir: "+dir);
           FileInformation fileInformation = new FileInformation(filename,size,filename,path,dir);
           return fileInformation;
        } else {
            throw new FileNotFoundException();
        }

    }
}
