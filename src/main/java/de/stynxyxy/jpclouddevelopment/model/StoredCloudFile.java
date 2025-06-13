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
    private String displayIcon;
    private boolean isDirectory;

    public StoredCloudFile(String fileName, String path, File jFile, long size, String fileType) {
        this.fileName = fileName;
        this.path = path;
        this.jFile = jFile;
        this.size = size;
        this.fileType = fileType;
        this.isDirectory = jFile.isDirectory();
        this.displayIcon = getIconForExtension();
    }
    private String getIconForExtension() {
        if (isDirectory) {
            return "/images/logos/folder.png";
        }

        if (fileType == null) {
            return "/images/logos/file.png";
        }

        int slashIndex = fileType.indexOf("/");
        if (slashIndex == -1) {
            return "/images/logos/file.png";
        }


        String mainType = fileType.substring(0, slashIndex);


        switch (mainType) {
            case "image":
            case "text":
            case "application":
            case "audio":
            case "video":
                return "/images/logos/" + mainType + ".png";
            default:
                return "/images/logos/file.png"; // Standardbild für andere Typen
        }
    }
}
