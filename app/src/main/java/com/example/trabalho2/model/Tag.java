package com.example.trabalho2.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Tag implements Serializable {
    private Long id;
    private String tag;

    public Tag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Tag(String tituloTag) {
        this.tag = tituloTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format(
                "Id: %d \n" +
                        "Título: %s \n" ,
                this.getId(),
                this.getTag());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getId().equals(tag.getId());
    }
}
