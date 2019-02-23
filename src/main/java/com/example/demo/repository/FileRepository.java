package com.example.demo.repository;

import com.example.demo.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 文件Repository
 * @author ggg1235
 */
public interface FileRepository extends JpaRepository<File,Long> {
    public List<File> findFileByFileName(String name);

    public List<File> findFileByFileType(Integer type);

    public List<File> findFileByNewsId(Long newsId);

    public List<File> findFileByUserId(Long userId);

    public List<File> findFileByOriginName(String originName);
}
