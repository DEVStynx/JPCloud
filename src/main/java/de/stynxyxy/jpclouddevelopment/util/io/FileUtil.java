package de.stynxyxy.jpclouddevelopment.util.io;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public class FileUtil {

    @Value("${jpcloud.branches.defaultbranch.label}")
    private static String defaultBranchLabel;
    @Value("${jpcloud.branches.defaultbranch.path}")
    private static String defaultBranchPath;
    public static StoredCloudFile MultipartToData(MultipartFile multipartFile, String path) {
        StoredCloudFile storedCloudFile = new StoredCloudFile(
                multipartFile.getName(),
                path,
                new File(path + File.separator + multipartFile.getName()),
                multipartFile.getSize(),
                multipartFile.getContentType());
        return storedCloudFile;
    }
    public static Branch getDefaultBranch() {
        return new Branch(defaultBranchLabel,defaultBranchPath);
    }

}
