package com.hao.movieshareback.vo;

public class VideoMergeFileVo {
    private String fileName;
    private String uniqueIdentifier;
    private String fileType;
    private Long size;

    public VideoMergeFileVo() {
    }

    public VideoMergeFileVo(String fileName, String uniqueIdentifier, String fileType, Long size) {
        this.fileName = fileName;
        this.uniqueIdentifier = uniqueIdentifier;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
