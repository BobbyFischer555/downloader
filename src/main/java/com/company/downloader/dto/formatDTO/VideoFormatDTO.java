package com.company.downloader.dto.formatDTO;

public class VideoFormatDTO {
    private String extension;
    private String quality;
    private String resolution;
    private String bitrate;
    private String listIndex;


    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getListIndex() {
        return listIndex;
    }

    public void setListIndex(String listIndex) {
        this.listIndex = listIndex;
    }
}
