package com.example.demo.repository;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户Repository
 * @author ggg1235
 */

public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * @param
     * @return
     */
    public User findByLoginName(String loginName);

    public List<User> findByUserType(Integer userType);


}
