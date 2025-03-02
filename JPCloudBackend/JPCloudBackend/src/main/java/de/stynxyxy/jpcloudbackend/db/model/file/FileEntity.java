package de.stynxyxy.jpcloudbackend.db.model.file;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "content")
    private byte[] filecontent;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "path",unique = true)
    private String path;

    public FileEntity(byte[] content, String name, String path) {
        this.name = name;
        this.filecontent = content;
        this.path = path;
    }
    public FileEntity() {}

    public byte[] getFilecontent() {
        return this.filecontent;
    }

    public String getName() {
        return this.name;
    }
    public String getPath() {
        return this.path;
    }
}
