package de.stynxyxy.jpclouddevelopment.controller.web;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.service.IO.branch.BranchValidationService;
import de.stynxyxy.jpclouddevelopment.service.IO.filestream.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
public class Dashboard {
    @Autowired
    FileIOService fileIOService;
    @Autowired
    BranchRepository branchRepository;
    @Value("${jpcloud.branches.defaultbranch.path}")
    private String defaultBranchPath;
    @Value("${jpcloud.branches.defaultbranch.label}")
    private String defaultBranchName;
    @Autowired
    BranchValidationService branchValidationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(name = "path",required = false,defaultValue = "") String path, @RequestParam(value = "branch", required = false, defaultValue = "-1") long branchId) {
        Branch branch;
        if (!branchRepository.existsById(branchId) || branchId == -1) {
            branch = branchRepository.getByLabel(defaultBranchName);
        } else {
            branch = branchRepository.getById(branchId);
        }

        if (!branchValidationService.isValid(branch)) {
            branchValidationService.createPath(branch);
        }

        File rootPath = new File(branch.getPath());
        File requestedPath = new File(rootPath.getAbsolutePath() + File.separator + path);
        model.addAttribute("branches",branchRepository.findAll());
        if (path.contains("../") || path.contains("..")) {
            model.addAttribute("files",fileIOService.getFiles(defaultBranchPath));
            return "dashboard";
        }
        model.addAttribute("files", fileIOService.getFiles(requestedPath.getAbsolutePath()));
        return "dashboard";
    }
}
