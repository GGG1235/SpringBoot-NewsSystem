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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * /admin 控制器
 * 返回thymeleaf页面
 * @author ggg1235
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserDetailService userDetailService;

    private static Map<Integer, String> newsCategory;
    static {
        newsCategory = new HashMap<>();
        newsCategory.put(1,"国内新闻");
        newsCategory.put(2,"国际新闻");
        newsCategory.put(3,"本地新闻");
        newsCategory.put(4,"娱乐新闻");
        newsCategory.put(5,"政要新闻");
    }

    private static Map<Integer, String> userRoles;
    static {
        userRoles = new HashMap<>();
        userRoles.put(1,"ADMIN");
        userRoles.put(2,"USER_S");
        userRoles.put(3,"USER");
    }

    private static Map<Integer, String> newsStatus;
    static {
        newsStatus = new HashMap<>();
        newsStatus.put(0,"未审核");
        newsStatus.put(1,"已审核");
    }

    private static String []sorts={"DESC","ASC"};

    @RequestMapping(value = {"/index"})
    public String getAdminIndex(Model model){
        model.addAttribute("adminCount",userService.getUserByType(1).size());
        model.addAttribute("newsCount1",newsService.getNewsByStatus(1).size());
        model.addAttribute("newsCount2",newsService.getNewsByStatus(0).size());
        model.addAttribute("userCount",userService.getAllUser().size());
        model.addAttribute("fileCount",fileService.getAllFile().size());
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));
        return "admin/index";
    }

    @RequestMapping(value = {"/",""})
    public String redirectAdminIndex(){
        return "redirect:/admin/index";
    }

    @RequestMapping(value = {"/manage-user"})
    public String getMangeUser(Model model,
                               @RequestParam(value = "sort",defaultValue = "1") Integer sort, //奇数为ASC,偶数为DESC
                               @RequestParam(value = "sortableFields",defaultValue = "userId") String sortableFields,
                               @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        if (pageIndex<1){
            pageIndex=1;
        }
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));

        String sortType=sorts[sort%2];
        Sort sort1 = "ASC".equals(sortType) ? new Sort(Sort.Direction.ASC, sortableFields) : new Sort(Sort.Direction.DESC, sortableFields);
        Pageable pageable=new PageRequest(pageIndex-1,pageSize,sort1);
        Page<User> userPage=userService.getAllUser(pageable);
        List<User> list = userPage.getContent();
        Map<Long,User> map=new ConcurrentHashMap<>();

        Integer pageMax=userPage.getTotalPages();
        if (pageSize>pageMax){
            pageSize=pageMax;
        }

        for (User user:list){
            map.put(user.getuserId(),user);
        }
        model.addAttribute("userList",list);
        model.addAttribute("userMap",map);
        model.addAttribute("page",userPage);
        model.addAttribute("roles",userRoles);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("pageMax",userPage.getTotalPages());

        return "admin/manage-user";
    }

    @RequestMapping(value = {"/update-user-detail/{id}"})
    public String getUpdateUserDetail(@PathVariable("id") Long id,Model model){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));
        User user=userService.getUserById(id);
        model.addAttribute("pUser",user);
        UserDetail userDetail=userDetailService.getUserDetailByUserId(user.getuserId());
        if (userDetail == null){
            model.addAttribute("exist",false);
        }else {
            model.addAttribute("exist",true);
            model.addAttribute("userDetail",userDetail);
        }

        return "admin/update-user-detail";
    }

    @RequestMapping(value = {"/manage-news"})
    public String getMangeNews(Model model,
                               @RequestParam(value = "sort",defaultValue = "1") Integer sort, //奇数为ASC,偶数为DESC
                               @RequestParam(value = "sortableFields",defaultValue = "newsId") String sortableFields,
                               @RequestParam(value = "category",defaultValue = "0") Integer category,
                               @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        if (pageIndex<1){
            pageIndex=1;
        }

        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));

        String sortType=sorts[sort%2];
        Sort sort1 = "ASC".equals(sortType) ? new Sort(Sort.Direction.ASC, sortableFields) : new Sort(Sort.Direction.DESC, sortableFields);
        Pageable pageable=new PageRequest(pageIndex-1,pageSize,sort1);
        Page<News> newsPage = newsService.getAllNews(pageable);
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

        return "admin/manage-news";
    }

    @RequestMapping(value = "/update-news/{id}")
    public String getUpdateNews(@PathVariable("id") Long id, Model model){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));
        model.addAttribute("status",newsStatus);
        model.addAttribute("news",newsService.getNewsById(id));

        return "admin/update-news";
    }

    @RequestMapping(value = "/add-news")
    public String getAddNews(Model model){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user",userService.getUserByLoginName(name));
        model.addAttribute("status",newsStatus);

        return "admin/add-news";
    }
}
