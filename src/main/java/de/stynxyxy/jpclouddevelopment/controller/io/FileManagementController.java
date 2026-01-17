package de.stynxyxy.jpclouddevelopment.controller.io;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import de.stynxyxy.jpclouddevelopment.service.IO.branch.BranchValidationService;
import de.stynxyxy.jpclouddevelopment.service.IO.filemanagement.FileManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

import static java.rmi.server.LogStream.log;

@Slf4j
@RestController
public class FileManagementController {
    @Autowired
    FileManagementService managementService;
    @Autowired
    BranchRepository branchRepo;

    @Value("${jpcloud.branches.defaultbranch.label}")
    private String defaultBranchLabel;
    @Autowired
    private BranchValidationService branchValidationService;

    @PostMapping("/file/createfolder")
    public ResponseEntity<?> createFolder(@RequestParam(name = "path") String path, @RequestParam(name = "branch") long  branch, @RequestParam(name = "name") String dirName) {
        Branch branchObject;
        if (branch == -1) {
            branchObject = branchRepo.findByLabel(defaultBranchLabel).orElseThrow();
            managementService.createFolder(branchObject,path + File.separator + dirName);
            return ResponseEntity.ok().build();
        } else {
            branchObject = branchRepo.findById(branch).orElseThrow();
            if (branchValidationService.isValid(branchObject)) {
                managementService.createFolder(branchObject,path + File.separator + dirName);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Bad branch");
            }
        }
    }
    @PostMapping("/file/deletefile")
    public ResponseEntity<?> deleteFile(@RequestParam(name = "path") String path, @RequestParam(name = "branch") long branchId) {
        System.out.println("deleete file");
        Branch branch;
        StoredCloudFile cloudFile;
        if (branchId == -1) {
            branch = branchRepo.findByLabel(defaultBranchLabel).orElseThrow();
        } else {
            branch = branchRepo.findById(branchId).orElseThrow();
        }
        if (!branchValidationService.isValid(branch))
            return ResponseEntity.badRequest().body("Bad branch");
        try {
            cloudFile = StoredCloudFile.getFile(branch,path);
        } catch (FileNotFoundException e) {
            return ResponseEntity.badRequest().body("File not Found");
        }
        managementService.deleteFile(cloudFile);
        return ResponseEntity.ok().build();
    }
}
