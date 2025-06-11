package de.stynxyxy.jpclouddevelopment.db.model.branch;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "branches")
@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "label", unique = true, nullable = false)
    private String label;

    @Column(name = "path", nullable = false)
    private String path;

    public Branch(String label, String path) {
        this.label = label;
        this.path = path;
    }

    public Branch() {
    }

}
