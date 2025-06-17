package de.stynxyxy.jpclouddevelopment.service.IO.filemanagement;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;

import java.io.File;

public interface FileManagementService {

    public void CopyFile(StoredCloudFile origin, String destinationPath, Branch destBranch);

    public void moveFile(StoredCloudFile origin, String destinationPath, Branch destBranch);

    public boolean deleteFile(StoredCloudFile file);

    public boolean createFolder(Branch branch, String path);
}
