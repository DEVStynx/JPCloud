package de.stynxyxy.jpclouddevelopment.controller.web;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import de.stynxyxy.jpclouddevelopment.service.IO.branch.BranchValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
public class FilePreviewController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchValidationService branchValidationService;

    @Value("${jpcloud.branches.defaultbranch.label}")
    private String defaultBranchLabel;

    @GetMapping("/file/preview")
    public String previewFile(@RequestParam("path") String path,
                              @RequestParam("branch") long branchId,
                              Model model) {

        // Branch-Handling wie in anderen Controllern
        Branch branch;
        if (!branchRepository.existsById(branchId) || branchId == -1) {
            branch = branchRepository.getByLabel(defaultBranchLabel);
        } else {
            branch = branchRepository.getById(branchId);
        }

        if (!branchValidationService.isValid(branch)) {
            branchValidationService.createPath(branch);
        }

        // Dateiinformationen sammeln
        File file = new File(branch.getPath() + File.separator + path);
        String fileName = file.getName();
        String fileType = getFileType(fileName);

        // Download-URL für die Datei erstellen
        String fileUrl = "/file/download?path=" + path + "&branch=" + branchId;

        // Informationen an die View übergeben
        model.addAttribute("fileUrl", fileUrl);
        model.addAttribute("fileName", fileName);
        model.addAttribute("fileType", fileType);
        model.addAttribute("path",path);

        return "filePreview";
    }

    private String getFileType(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}