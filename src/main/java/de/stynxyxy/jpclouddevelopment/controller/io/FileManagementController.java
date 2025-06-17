package de.stynxyxy.jpclouddevelopment.controller.io;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.service.IO.branch.BranchValidationService;
import de.stynxyxy.jpclouddevelopment.service.IO.filemanagement.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

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
}
