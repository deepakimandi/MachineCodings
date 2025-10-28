package org.example.CustomerIssueResolution1.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
public class Rider {
    String id;
    String name;

    public Rider(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}