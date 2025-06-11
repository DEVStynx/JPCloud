package de.stynxyxy.jpclouddevelopment.model;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import lombok.Data;

import java.io.File;

@Data
public class StoredCloudFile {
    private String fileName;
    private String path;
    private File jFile;
    private long size;
    private String fileType;

    public StoredCloudFile(String fileName, String path, File jFile, long size, String fileType) {
        this.fileName = fileName;
        this.path = path;
        this.jFile = jFile;
        this.size = size;
        this.fileType = fileType;
    }
}
