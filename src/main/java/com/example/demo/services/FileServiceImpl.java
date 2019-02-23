package com.example.demo.services;

import com.example.demo.models.File;
import com.example.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File saveOrUpdateFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    @Override
    public List<File> getAllFile() {
        return fileRepository.findAll();
    }

    @Override
    public List<File> getFileByType(Integer type) {
        return fileRepository.findFileByFileType(type);
    }

    @Override
    public List<File> getFileByUserId(Long userId) {
        return fileRepository.findFileByUserId(userId);
    }

    @Override
    public List<File> getFileByFileName(String fileName) {
        return fileRepository.findFileByFileName(fileName);
    }

    @Override
    public List<File> getFileByOrginName(String orginName) {
        return fileRepository.findFileByOriginName(orginName);
    }

    @Override
    public File getFileById(Long newsId) {
        return (File) fileRepository.findFileByNewsId(newsId);
    }
}
