package de.stynxyxy.jpclouddevelopment.service.IO.filestream;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import de.stynxyxy.jpclouddevelopment.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class FileIOServiceImpl implements FileIOService{
    private Logger logger = Logger.getLogger(getClass().getSimpleName());
    @Value("${jpcloud.branches.defaultbranch.path}")
    private String defaultBranchPath;
    @Override
    public void uploadFile(MultipartFile file, StoredCloudFile fileData, Branch branch) {
        try {
            File targetPath = new File(branch.getPath() + File.separator + fileData.getPath() + File.separator+file.getOriginalFilename());
            FileUtil.ensureDirectorysExists(targetPath.getAbsoluteFile());
            InputStream fileContent = file.getInputStream();
            Files.copy(fileContent, Path.of(targetPath.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> downloadFile(StoredCloudFile fileData, Branch branch) {
        File rootPath = new File(branch.getPath());
        logger.info("Filedata raw path: "+fileData.getPath());
        logger.info("rootPath: "+rootPath.getAbsolutePath());
        File downloadFile = new File(rootPath.getAbsolutePath() + File.separator + fileData.getPath());
        logger.info("download path: "+downloadFile.getAbsolutePath());
        if (!downloadFile.exists() ) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error?message="+ "There was an Issue downloading the File, as it doesnt exist!");
            logger.warning("didn't find file: "+downloadFile.getAbsoluteFile());
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }
        if (downloadFile.isDirectory()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/dashboard?path="+ fileData.getPath().replace("\\","/"));
            return new ResponseEntity<String>(headers,HttpStatus.FOUND);
        }
        try {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"")
                    .body(new InputStreamResource(new FileInputStream(downloadFile)));
        } catch (IOException e) {
            Logger.getLogger(FileIOService.class.getSimpleName()).warning(e.getMessage());
            Logger.getLogger(FileIOService.class.getSimpleName()).warning(e.getStackTrace().toString());

            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    public Set<StoredCloudFile> getFiles(String path) {

            File file = new File(path);
            if (!file.exists() || file == null) {
                logger.warning("File doesnt exist : "+path);
                return Set.of();
            }
            File rootPath = new File(defaultBranchPath);
            File dtmPath = new File(path.replace(rootPath.getAbsolutePath(),""));

            if (!file.isDirectory()) {
                return Set.of(new StoredCloudFile(file.getName(),path + File.separator + file.getName(),file,file.getTotalSpace(),file.getName()));
            }
            Set<StoredCloudFile> files = new HashSet<>();
            for (File subFile : file.listFiles()) {
                try {
                    files.add(new StoredCloudFile(subFile.getName(),subFile.getAbsolutePath().replace(rootPath.getAbsolutePath(),""),subFile,subFile.getTotalSpace(),Files.probeContentType(Path.of(subFile.getAbsolutePath()))));
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }
            return files;
        }
        @Override
        public Set<StoredCloudFile> getFiles(String path, Branch branch) {
            Set<StoredCloudFile> files = new HashSet<>();

            File file = new File(branch.getPath() + File.separator + path);
            logger.info("recieved info in method : "+file.getAbsolutePath());
            if (!file.exists() || file == null) {
                logger.warning("File doesnt exist : "+file.getAbsolutePath());
                return Set.of();
            }

            if (!file.isDirectory()) {
                //TODO dont pass original file, because of absolute path and so on
                logger.warning("File isnt dir : "+path);
                return Set.of(new StoredCloudFile(file.getName(),path ,file,file.getTotalSpace(),file.getName()));
            }
            for (File subFile : file.listFiles()) {
                logger.warning("subfile: : "+subFile.getName());
                try {
                    StoredCloudFile subcFile = new StoredCloudFile(subFile.getName(),path + File.separator + subFile.getName(),subFile, subFile.getTotalSpace(),Files.probeContentType(Path.of(subFile.getAbsolutePath())),branch);
                    files.add(subcFile);
                } catch (IOException e) {
                    logger.warning(e.getMessage());
                }
            }


            return files;
        }


}
