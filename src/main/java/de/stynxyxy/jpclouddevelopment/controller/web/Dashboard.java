package de.stynxyxy.jpclouddevelopment.controller.web;

import de.stynxyxy.jpclouddevelopment.JpCloudDevelopmentApplication;
import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.model.StoredCloudFile;
import de.stynxyxy.jpclouddevelopment.service.IO.filestream.FileIOService;
import de.stynxyxy.jpclouddevelopment.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

@Controller
public class Dashboard {
    @Autowired
    FileIOService fileIOService;
    @Value("${jpcloud.branches.defaultbranch.path}")
    private String defaultBranchPath;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(name = "path",required = false,defaultValue = "") String path) {
        File rootPath = new File(defaultBranchPath);
        System.out.println(rootPath.getAbsolutePath());
        File requestedPath = new File(rootPath.getAbsolutePath() + File.separator + path);
        model.addAttribute("files", fileIOService.getFiles(requestedPath.getAbsolutePath()));
        return "dashboard";
    }
}
