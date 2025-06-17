package de.stynxyxy.jpclouddevelopment.db.model.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    Optional<Branch> findByLabel(String label);

    boolean existsByLabel(String label);

    Branch getByLabel(String label);
}
