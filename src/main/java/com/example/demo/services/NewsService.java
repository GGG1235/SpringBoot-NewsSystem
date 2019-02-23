package com.example.demo.services;

import com.example.demo.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    public News saveOrUpdateNews(News news);

    public List<News> getNewsByType(Integer type);

    public List<News> getNewsByStatus(Integer status);

    public List<News> getNewsByTitle(String title);

    public List<News> getNewsByTypeAndStatus(Integer type,Integer status);

    public Page<News> getNewsByTypeAndStatus(Integer type, Integer status, Pageable pageable);

    public Page<News> getAllNews(Pageable pageable);

    public News getNewsById(Long id);

    public void removeNews(Long id);

    public Page<News> getAllNewsByType(Integer type,Pageable pageable);

    public List<News> getNewsByUserId(Long id);

    public List<News> getNewsByUserIdAndNewsStatus(Long userId,Integer status);

    public Page<News> getNewsByUserId(Long userId,Pageable pageable);

    public List<News> getAllNews();

}
