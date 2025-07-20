package de.stynxyxy.jpclouddevelopment.service.IO.filestream;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

public interface FileIOService {
    /**
     * Used to upload files to the cloud
     * @param file The file given by the client
     */
    public void uploadFile(MultipartFile file, StoredCloudFile fileData, Branch branch);
    /**
     * Used to download files from the cloud
     * @param fileData The {@link StoredCloudFile FileData}
     */
    public ResponseEntity<?> downloadFile(StoredCloudFile fileData, Branch branch);
    public Set<StoredCloudFile> getFiles(String path,Branch branch);
}
