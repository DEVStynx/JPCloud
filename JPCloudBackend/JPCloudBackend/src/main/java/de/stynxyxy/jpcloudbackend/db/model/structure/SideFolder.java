package de.stynxyxy.jpcloudbackend.db.model.structure;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "sidefolders")
public class SideFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(
            nullable = false,
            unique = true)
    @Getter
    private String label;

    @Column(
            nullable = false,
            unique = true)
    @Getter
    private String path;

    public SideFolder(long id, String label, String path) {
        this.id = id;
        this.label = label;
        this.path = path;
    }

    public SideFolder() {}



}
