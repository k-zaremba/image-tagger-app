package com.sunner.imageatagger.database.model;

import javax.persistence.*;

@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(name = "creation_date")
    private String creationDate;

    public Entry() {
    }

    public Entry(String url, String creationDate) {
        this.url = url;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
