package com.example.demo.services;

import com.example.demo.models.UserDetail;

public interface UserDetailService {

    public UserDetail getUserDetailByUserId(Long userId);

    public UserDetail saveOrUpdateUserDetail(UserDetail userDetail);

    public void removeByUserId(Long userId);

    public void removeById(Long id);
}
