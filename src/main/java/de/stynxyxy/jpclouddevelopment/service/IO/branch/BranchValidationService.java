package de.stynxyxy.jpclouddevelopment.service.IO.branch;


import de.stynxyxy.jpclouddevelopment.db.model.branch.Branch;

public interface BranchValidationService {
    public boolean isValid(Branch branch);

    public void createPath(Branch branch);
}
