package com.hao.movieshareback.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class Chunk implements Serializable {
    private String chunkId;
    private Integer chunkNumber;
    private Integer chunkSize;
    private Integer currentChunkSize;
    private Long totalSize;
    private String identifier;
    private String filename;
    private String relativePath;
    private Integer totalChunks;
    private MultipartFile file;

    public Chunk() {
    }

    public Chunk(Integer chunkNumber, Integer chunkSize, Integer currentChunkSize,
                 Long totalSize, String identifier, String filename, String relativePath, Integer totalChunks) {
        this.chunkNumber = chunkNumber;
        this.chunkSize = chunkSize;
        this.currentChunkSize = currentChunkSize;
        this.totalSize = totalSize;
        this.identifier = identifier;
        this.filename = filename;
        this.relativePath = relativePath;
        this.totalChunks = totalChunks;
    }

    public String getChunkId() {
        return chunkId;
    }

    public void setChunkId(String chunkId) {
        this.chunkId = chunkId;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public void setChunkNumber(Integer chunkNumber) {
        this.chunkNumber = chunkNumber;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getCurrentChunkSize() {
        return currentChunkSize;
    }

    public void setCurrentChunkSize(Integer currentChunkSize) {
        this.currentChunkSize = currentChunkSize;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Integer getTotalChunks() {
        return totalChunks;
    }

    public void setTotalChunks(Integer totalChunks) {
        this.totalChunks = totalChunks;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
