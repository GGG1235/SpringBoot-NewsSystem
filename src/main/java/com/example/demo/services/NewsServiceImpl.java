package com.example.demo.services;

import com.example.demo.models.News;
import com.example.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    /**
     * @param news
     * @return
     */
    @Override
    public News saveOrUpdateNews(News news) {
        return newsRepository.save(news);
    }

    /**
     * @param type
     * @return
     */
    @Override
    public List<News> getNewsByType(Integer type) {
        return newsRepository.getNewsByNewsType(type);
    }

    /**
     * @param status
     * @return
     */
    @Override
    public List<News> getNewsByStatus(Integer status) {
        return newsRepository.getNewsByNewsStatus(status);
    }

    /**
     * @param title
     * @return
     */
    @Override
    public List<News> getNewsByTitle(String title) {
        return newsRepository.getNewsByNewsTitle(title);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public News getNewsById(Long id) {
        return newsRepository.getOne(id);
    }

    /**
     * @param id
     */
    @Override
    public void removeNews(Long id) {
        newsRepository.deleteById(id);
    }

    /**
     * @param type
     * @param status
     * @return
     */
    @Override
    public List<News> getNewsByTypeAndStatus(Integer type, Integer status) {
        return newsRepository.getNewsByNewsTypeAndNewsStatus(type,status);
    }

    /**
     * @param type
     * @param status
     * @param pageable
     * @return
     */
    @Override
    public Page<News> getNewsByTypeAndStatus(Integer type, Integer status, Pageable pageable) {
        return newsRepository.getNewsByNewsTypeAndNewsStatus(type,status,pageable);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<News> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    /**
     * @param type
     * @param pageable
     * @return
     */
    @Override
    public Page<News> getAllNewsByType(Integer type, Pageable pageable) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<News> getNewsByUserId(Long id) {
        return newsRepository.getNewsByUserId(id);
    }

    /**
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<News> getNewsByUserIdAndNewsStatus(Long userId, Integer status) {
        return newsRepository.getNewsByUserIdAndNewsStatus(userId, status);
    }

    /**
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public Page<News> getNewsByUserId(Long userId, Pageable pageable) {
        return newsRepository.getNewsByUserId(userId,pageable);
    }


    /**
     * @return
     */
    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
