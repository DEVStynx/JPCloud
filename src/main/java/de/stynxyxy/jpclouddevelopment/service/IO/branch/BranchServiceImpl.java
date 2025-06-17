package de.stynxyxy.jpclouddevelopment.service.IO.branch;

import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;
import de.stynxyxy.jpclouddevelopment.db.model.branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BranchServiceImpl implements BranchService{
    @Autowired
    private BranchRepository branchRepo;
    @Autowired
    BranchValidationService branchValidationService;

    @Override
    public void createBranch(Branch branch) {
        if (!branchValidationService.isValid(branch)) {
            branchValidationService.createPath(branch);
        }
        if (branchRepo.existsByLabel(branch.getLabel())) {
            Branch existing = branchRepo.getByLabel(branch.getLabel());
            existing.setPath(branch.getPath());
            branchRepo.save(existing);
            return;
        }
        branchRepo.save(branch);
    }
}
