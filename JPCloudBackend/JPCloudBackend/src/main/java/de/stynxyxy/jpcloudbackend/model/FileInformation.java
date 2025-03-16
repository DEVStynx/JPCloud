package de.stynxyxy.jpcloudbackend.model;

import lombok.Getter;

public class FileInformation {
    @Getter
    private final String name;

    @Getter
    private final long size;

    @Getter
    private final String type;

    @Getter
    private final boolean dir;

    @Getter
    private final String path;

    public FileInformation(String name, long size, String type, String path, boolean dir) {
        this.name = name;
        this.size = size;
        this.type = type;

        this.path = path;
        this.dir = dir;
    }

}