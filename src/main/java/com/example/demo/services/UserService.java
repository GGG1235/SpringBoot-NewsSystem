package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;

public interface UserService {

    public User saveOrUpdateUser(User user);

    public User registerUser(User user);

    public User getUserById(Long id);

    public User getUserByLoginName(String loginName);

    public void removeUser(Long id);

    public List<User> getUserByType(Integer userType);

    public Page<User> getAllUser(Pageable pageable);

    public List<User> getAllUser();

}
