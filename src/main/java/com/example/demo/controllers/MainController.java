package com.example.demo.controllers;

import com.example.demo.models.News;
import com.example.demo.models.User;
import com.example.demo.services.NewsService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 新闻前台控制器
 * 返回thymeleaf页面
 * @author ggg1235
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    private static String []sorts={"DESC","ASC"};

    @GetMapping(value = {"","/","/index"})
    public String getIndex(Model model){

        Integer sort=0;
        String sortableFields="auditTime";

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        String sortType=sorts[sort%2];
        Sort sort1 = "ASC".equals(sortType) ? new Sort(Sort.Direction.ASC, sortableFields) : new Sort(Sort.Direction.DESC, sortableFields);
        Pageable pageable=new PageRequest(0,5,sort1);

        Page<News> page1=newsService.getNewsByTypeAndStatus(1,1,pageable);
        List<News> list1 = page1.getContent();

        Page<News> page2=newsService.getNewsByTypeAndStatus(2,1,pageable);
        List<News> list2 = page2.getContent();

        Page<News> page3=newsService.getNewsByTypeAndStatus(3,1,pageable);
        List<News> list3 = page3.getContent();

        Page<News> page4=newsService.getNewsByTypeAndStatus(4,1,pageable);
        List<News> list4 = page4.getContent();

        Page<News> page5=newsService.getNewsByTypeAndStatus(5,1,pageable);
        List<News> list5 = page5.getContent();

        model.addAttribute("list1",list1);
        model.addAttribute("list2",list2);
        model.addAttribute("list3",list3);
        model.addAttribute("list4",list4);
        model.addAttribute("list5",list5);

        model.addAttribute("isLogin",user != null);
        if (user!=null){
            model.addAttribute("user",user);
        }

        return "index";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败,用户名或者密码错误!");
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(){
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @GetMapping("/register-error")
    public String registerError(Model model){
        model.addAttribute("registerError", true);
        model.addAttribute("errorMsg", "注册失败,用户名被占用!");
        return "register";
    }

    @PostMapping("/edit-user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String editUser(User user){

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user2=userService.getUserByLoginName(name);

        if (!user2.getloginName().equals(user.getloginName())){
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }

        user.setuserId(user2.getuserId());
        user.setuserType(user2.getuserType());
        user.setloginPwd(DigestUtils.md5DigestAsHex(user.getloginPwd().getBytes()));
        user.setuserCreate(userService.getUserById(user.getuserId()).getuserCreate());
        User user1=userService.saveOrUpdateUser(user);
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(User user) {

        if(userService.getUserByLoginName(user.getloginName())!=null) {
            return "redirect:/register-error";
        }

        user.setuserType(3);
        user.setloginPwd(DigestUtils.md5DigestAsHex(user.getloginPwd().getBytes()));
        userService.registerUser(user);
        return "redirect:/login";
    }

}
