package com.example.demo.services;

import com.example.demo.models.File;

import java.util.List;

public interface FileService {

    public File saveOrUpdateFile(File file);

    public void removeFile(Long fileId);

    public List<File> getAllFile();

    public List<File> getFileByType(Integer type);

    public List<File> getFileByUserId(Long userId);

    public List<File> getFileByFileName(String fileName);

    public List<File> getFileByOrginName(String orginName);

    public File getFileById(Long newsId);

}
