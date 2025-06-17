package de.stynxyxy.jpclouddevelopment.service.IO.branch;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Primary
public class BranchValidationServiceImpl implements BranchValidationService{

    @Override
    public boolean isValid(Branch branch) {
        File sysFile = new File(branch.getPath());
        return sysFile.exists() && sysFile.isDirectory();
    }

    @Override
    public void createPath(Branch branch) {
        File sysFile = new File(branch.getPath());

        if (!sysFile.exists() && !sysFile.getParentFile().exists()) {
            sysFile.mkdirs();
        }
    }
}
