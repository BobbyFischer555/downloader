package com.company.downloader.dto;

public class VideoDetails {
    private String author;
    private String title;
    private String length;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLengt() {
        return length;
    }

    public void setLengt(String lengt) {
        this.length = lengt;
    }
}
