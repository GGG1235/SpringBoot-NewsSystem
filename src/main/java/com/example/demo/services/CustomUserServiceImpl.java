package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * 重写UserDetailsService接口,SpringSecurity需要
 * Create By 2019/1/06
 * @author ggg1235
 */
public class CustomUserServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByLoginName(s);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }
//        System.out.println(String.format("s:%s",s));
//        System.out.println(String.format("username:%s,password:%s",user.getUsername(),user.getPassword()));
//        System.out.println(user.getAuthorities());
        return user;
    }
}
