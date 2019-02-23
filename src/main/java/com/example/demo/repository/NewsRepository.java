package com.example.demo.repository;

import com.example.demo.models.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 新闻Repository
 * @author ggg1235
 */

public interface NewsRepository extends JpaRepository<News,Long> {

    public List<News> getNewsByNewsTitle(String title);

    public List<News> getNewsByNewsStatus(Integer status);

    public List<News> getNewsByNewsType(Integer type);

    public List<News> getNewsByNewsTypeAndNewsStatus(Integer type,Integer status);

    public Page<News> getNewsByNewsTypeAndNewsStatus(Integer type, Integer status, Pageable pageable);

    public Page<News> getNewsByNewsType(Integer type,Pageable pageable);

    public List<News> getNewsByUserId(Long userId);

    public List<News> getNewsByUserIdAndNewsStatus(Long userId,Integer status);

    public Page<News> getNewsByUserId(Long userId,Pageable pageable);

}
