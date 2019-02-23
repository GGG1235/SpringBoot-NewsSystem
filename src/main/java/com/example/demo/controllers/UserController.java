package com.example.demo.controllers;

import com.example.demo.models.News;
import com.example.demo.models.User;
import com.example.demo.models.UserDetail;
import com.example.demo.services.FileService;
import com.example.demo.services.NewsService;
import com.example.demo.services.UserDetailService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 非管理员用户编辑页,用户管理页面
 * 返回thymeleaf页面
 * @author ggg1235
 */
@Controller
@PreAuthorize("hasRole('ROLE_USER_S')")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FileService fileService;

    private static Map<Integer, String> newsCategory;
    static {
        newsCategory = new HashMap<>();
        newsCategory.put(1,"国内新闻");
        newsCategory.put(2,"国际新闻");
        newsCategory.put(3,"本地新闻");
        newsCategory.put(4,"娱乐新闻");
        newsCategory.put(5,"政要新闻");
    }

    private static Map<Integer, String> newsStatus;
    static {
        newsStatus = new HashMap<>();
        newsStatus.put(0,"未审核");
        newsStatus.put(1,"已审核");
    }

    private static String []sorts={"DESC","ASC"};

    @RequestMapping(value = {"","/index","/"})
    public ModelAndView getUserIndex(Model model){

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        model.addAttribute("newsCount1",newsService.getNewsByUserIdAndNewsStatus(user.getuserId(),1).size());
        model.addAttribute("newsCount2",newsService.getNewsByUserIdAndNewsStatus(user.getuserId(),0).size());
        model.addAttribute("fileCount",fileService.getFileByUserId(user.getuserId()).size());

        model.addAttribute("user",user);

        return new ModelAndView("user/index","userIndex",model);
    }

    @RequestMapping("/userDetail")
    public ModelAndView updateUserDetail(Model model){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        model.addAttribute("user",user);

        model.addAttribute("pUser",user);
        UserDetail userDetail=userDetailService.getUserDetailByUserId(user.getuserId());
        if (userDetail == null){
            model.addAttribute("exist",false);
        }else {
            model.addAttribute("exist",true);
            model.addAttribute("userDetail",userDetail);
        }

        return new ModelAndView("user/update-detail","userDetailModel",model);
    }

    @RequestMapping("/view-article")
    public ModelAndView getViewArticle(Model model,
                                       @RequestParam(value = "sort",defaultValue = "1") Integer sort, //奇数为ASC,偶数为DESC
                                       @RequestParam(value = "sortableFields",defaultValue = "userId") String sortableFields,
                                       @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        if (pageIndex<1){
            pageIndex=1;
        }

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        model.addAttribute("user",user);


        String sortType=sorts[sort%2];
        Sort sort1 = "ASC".equals(sortType) ? new Sort(Sort.Direction.ASC, sortableFields) : new Sort(Sort.Direction.DESC, sortableFields);
        Pageable pageable=new PageRequest(pageIndex-1,pageSize,sort1);
        Page<News> newsPage = newsService.getNewsByUserId(user.getuserId(),pageable);

        List<News> list = newsPage.getContent();

        Integer pageMax=newsPage.getTotalPages();
        if (pageSize>pageMax){
            pageSize=pageMax;
        }

        model.addAttribute("newsList",list);
        model.addAttribute("category",newsCategory);
        model.addAttribute("status",newsStatus);
        model.addAttribute("page",newsPage);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("pageMax",newsPage.getTotalPages());

        return new ModelAndView("user/view-article","articleModel",model);
    }

    @RequestMapping("/post-article")
    public ModelAndView getPostArticle(Model model){

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.getUserByLoginName(name);

        model.addAttribute("user",user);
        model.addAttribute("status",newsStatus);

        return new ModelAndView("user/post-article","articleModel",model);
    }

}
