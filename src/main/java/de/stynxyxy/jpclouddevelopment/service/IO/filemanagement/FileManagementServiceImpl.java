package de.stynxyxy.jpclouddevelopment.service.IO.filemanagement;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class FileManagementServiceImpl implements FileManagementService{
    private Logger logger = Logger.getLogger(FileManagementService.class.getSimpleName());
    @Override
    public void CopyFile(StoredCloudFile origin, String destinationPath, Branch destBranch) {

    }

    @Override
    public void moveFile(StoredCloudFile origin, String destinationPath, Branch destBranch) {
        try {
            File originFile = origin.getJFile();
            File destination = new File(destBranch.getPath() + File.separator + destinationPath + File.separator + originFile.getName());
            if (!originFile.exists() ) {
                return;
            }
            File parentDir = destination.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            Files.move(
                    originFile.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }catch (Exception e) {
            logger.warning(e.getMessage());
        }

    }

    @Override
    public boolean deleteFile(StoredCloudFile file) {
        File systemFile = new File(file.getPath());
        if (file.getJFile() == null || !systemFile.exists()) {
            return false;
        }
        return systemFile.delete();
    }

    @Override
    public boolean createFolder(Branch branch, String path) {
        File systemFile = new File(branch.getPath() + File.separator + path);
        if (systemFile.exists()) {
            return false;
        }
        return systemFile.mkdirs();
    }
}
