package de.stynxyxy.jpclouddevelopment.util.io;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;


@Slf4j
public class FileUtil {

    @Value("${jpcloud.branches.defaultbranch.label}")
    private static String defaultBranchLabel;
    @Value("${jpcloud.branches.defaultbranch.path}")
    private static String defaultBranchPath;
    private static Logger logger = Logger.getLogger(FileUtil.class.getSimpleName());
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

    public static void ensureDirectorysExists(File file) {
        if (!file.exists() && !file.isFile()) {
            file.mkdirs();
        }
    }
    public static String getFileExtension(String name) {
        StringBuilder nameBuilder = new StringBuilder(name);
        int dot = nameBuilder.lastIndexOf(".");
        return name.substring(dot+1,name.length());
    }
}
