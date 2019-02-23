package com.example.demo.repository;

import com.example.demo.models.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 新闻详情Repository
 * @author ggg1235
 */

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {

    public UserDetail findByUserId(Long userId);

    public void removeByUserId(Long userId);

}
